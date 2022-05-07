package com.example.demo.thread;

import com.example.demo.JarLauncher;
import com.example.demo.common.singleton.ClassDefinedSingleton;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BeanClassThread implements Callable<List<String> > {
    private static final List<String> fileList = new ArrayList<>();



    public void run() {
        System.out.println("Thread classLoader:"+this.getClass().getClassLoader());
        URL rootUrl = JarLauncher.class.getResource("/");
        if(rootUrl!=null) {
            File rootFile = new File(rootUrl.getPath());
            getChildFile(rootUrl.getPath(),rootFile,fileList);
        }
    }

    private void getChildFile(String rootPath,File parrent, List<String> fileList){
        if(parrent.isDirectory()){
            File[] children = parrent.listFiles();
            if(children!=null) {
                for (File child : children) {
                    getChildFile(rootPath,child, fileList);
                }
            }
        }else{
            if(parrent.getPath().endsWith(".class")) {
                String classFileName = parrent.getPath().replace(rootPath,"");
                int indot = classFileName.lastIndexOf(".class");
                classFileName = classFileName.substring(0,indot);
                classFileName = classFileName.replace("/",".");
                fileList.add(classFileName);
            }
        }
    }

    @Override
    public List<String> call() throws Exception {
        run();
        return fileList;
    }
}
