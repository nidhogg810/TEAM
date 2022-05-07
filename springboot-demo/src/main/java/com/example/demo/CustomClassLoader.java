package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;

public class CustomClassLoader extends URLClassLoader {
    private static final int BUFFER_SIZE = 4096;

    private static final Map<String,Class<?>> classMap = new ConcurrentHashMap<>();


    static {
        ClassLoader.registerAsParallelCapable();
    }

    private final boolean exploded;

    private final Archive rootArchive;

    private final Object packageLock = new Object();

    private volatile DefinePackageCallType definePackageCallType;

    public CustomClassLoader(URL[] urls, ClassLoader parent) {
        this(false, urls, parent);
    }

    public CustomClassLoader(boolean exploded, URL[] urls, ClassLoader parent) {
        this(exploded, null, urls, parent);
    }

    public CustomClassLoader(boolean exploded, Archive rootArchive, URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.exploded = exploded;
        this.rootArchive = rootArchive;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("com.example.demo.")) {
            try {
                Class<?> result = loadClassInLaunchedClassLoader(name);
                if (resolve) {
                    resolveClass(result);
                }
                return result;
            } catch (ClassNotFoundException e) {

            }
        }
        if (this.exploded) {
            return super.loadClass(name, resolve);
        }

        try {
            definePackageIfNecessary(name);
        } catch (IllegalArgumentException e) {
            // Tolerate race condition due to being parallel capable
            if (getPackage(name) == null) {
                // This should never happen as the IllegalArgumentException indicates
                // that the package has already been defined and, therefore,
                // getPackage(name) should not return null.
                throw new AssertionError("Package " + name + " has already been defined but it could not be found");
            }
        }
        return super.loadClass(name, resolve);

    }

    private Class<?> loadClassInLaunchedClassLoader(String name) throws ClassNotFoundException {
        if(!classMap.containsKey(name)) {
            String internalName = name.replace('.', '/') + ".class";
            InputStream inputStream = getParent().getResourceAsStream(internalName);
            if (inputStream == null) {
                throw new ClassNotFoundException(name);
            }
            try {
                try {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    inputStream.close();
                    byte[] bytes = outputStream.toByteArray();
                    Class<?> definedClass = defineClass(name, bytes, 0, bytes.length);
                    definePackageIfNecessary(name);
                    classMap.put(name,definedClass);
                    return definedClass;
                } finally {
                    inputStream.close();
                }
            } catch (IOException ex) {
                throw new ClassNotFoundException("Cannot load resource for class [" + name + "]", ex);
            }
        }else{
            return classMap.get(name);
        }
    }

    private void definePackageIfNecessary(String className) {
        int lastDot = className.lastIndexOf('.');
        if (lastDot >= 0) {
            String packageName = className.substring(0, lastDot);
            if (getPackage(packageName) == null) {
                try {
                    definePackage(className, packageName);
                } catch (IllegalArgumentException ex) {
                    // Tolerate race condition due to being parallel capable
                    if (getPackage(packageName) == null) {
                        // This should never happen as the IllegalArgumentException
                        // indicates that the package has already been defined and,
                        // therefore, getPackage(name) should not have returned null.
                        throw new AssertionError(
                                "Package " + packageName + " has already been defined but it could not be found");
                    }
                }
            }
        }
    }

    private void definePackage(String className, String packageName) {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
                String packageEntryName = packageName.replace('.', '/') + "/";
                String classEntryName = className.replace('.', '/') + ".class";
                for (URL url : getURLs()) {
                    try {
                        URLConnection connection = url.openConnection();
                        if (connection instanceof JarURLConnection) {
                            JarFile jarFile = ((JarURLConnection) connection).getJarFile();
                            if (jarFile.getEntry(classEntryName) != null && jarFile.getEntry(packageEntryName) != null
                                    && jarFile.getManifest() != null) {
                                definePackage(packageName, jarFile.getManifest(), url);
                                return null;
                            }
                        }
                    } catch (IOException ex) {
                        // Ignore
                    }
                }
                return null;
            }, AccessController.getContext());
        } catch (java.security.PrivilegedActionException ex) {

        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * The different types of call made to define a package. We track these for exploded
     * jars so that we can detect packages that should have manifest attributes applied.
     */
    private enum DefinePackageCallType {

        /**
         * A define package call from a resource that has a manifest.
         */
        MANIFEST,

        /**
         * A define package call with a direct set of attributes.
         */
        ATTRIBUTES

    }
}
