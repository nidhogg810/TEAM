package com.example.demo.cache.annotation;

import org.springframework.cache.annotation.CacheAnnotationParser;
import org.springframework.cache.interceptor.CacheOperation;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class HCacheAnnotationParser implements CacheAnnotationParser, Serializable {
    private static final Set<Class<? extends Annotation>> CACHE_OPERATION_ANNOTATIONS = new LinkedHashSet<>(8);

    @Override
    public Collection<CacheOperation> parseCacheAnnotations(Class<?> type) {
        return null;
    }

    @Override
    public Collection<CacheOperation> parseCacheAnnotations(Method method) {
        return null;
    }
//
//    static {
////        CACHE_OPERATION_ANNOTATIONS.add(Cacheable.class);
////        CACHE_OPERATION_ANNOTATIONS.add(CacheEvict.class);
////        CACHE_OPERATION_ANNOTATIONS.add(CachePut.class);
////        CACHE_OPERATION_ANNOTATIONS.add(Caching.class);
//        CACHE_OPERATION_ANNOTATIONS.add(HCacheable.class);
//        CACHE_OPERATION_ANNOTATIONS.add(HCachePut.class);
//        CACHE_OPERATION_ANNOTATIONS.add(HCacheEvict.class);
//    }
//
//    @Override
//    public boolean isCandidateClass(Class<?> targetClass) {
//        return AnnotationUtils.isCandidateClass(targetClass, CACHE_OPERATION_ANNOTATIONS);
//    }
//
//    @Override
//    @Nullable
//    public Collection<CacheOperation> parseCacheAnnotations(Class<?> type) {
//        DefaultCacheConfig defaultConfig = new DefaultCacheConfig(type);
//        return parseCacheAnnotations(defaultConfig, type);
//    }
//
//    @Override
//    public Collection<CacheOperation> parseCacheAnnotations(Method method) {
//        return null;
//    }
//
//    @Nullable
//    private Collection<CacheOperation> parseCacheAnnotations(DefaultCacheConfig defaultCacheConfig, AnnotatedElement ae) {
//
//    }
//
//    @Nullable
//    private Collection<CacheOperation> parseCacheAnnotations(DefaultCacheConfig cachingConfig, AnnotatedElement ae, boolean localOnly) {
//        Collection<? extends Annotation> anns = (localOnly ?
//                AnnotatedElementUtils.getAllMergedAnnotations(ae, CACHE_OPERATION_ANNOTATIONS) :
//                AnnotatedElementUtils.findAllMergedAnnotations(ae, CACHE_OPERATION_ANNOTATIONS));
//        if (anns.isEmpty()) {
//            return null;
//        }
//        final Collection<CacheOperation> ops = new ArrayList<>(1);
//        anns.stream().filter(ann -> ann instanceof HCacheable).forEach(
//                ann -> ops.add(parseCacheableAnnotation(ae, cachingConfig, (HCacheable) ann)));
//        anns.stream().filter(ann -> ann instanceof HCacheEvict).forEach(
//                ann -> ops.add(parseEvictAnnotation(ae, cachingConfig, (HCacheEvict) ann)));
//        anns.stream().filter(ann -> ann instanceof HCachePut).forEach(
//                ann -> ops.add(parsePutAnnotation(ae, cachingConfig, (HCachePut) ann)));
//        return ops;
//    }
//
//    private CacheOperation parsePutAnnotation(AnnotatedElement ae, DefaultCacheConfig cachingConfig, HCachePut ann) {
//    }
//
//    private CacheOperation parseEvictAnnotation(AnnotatedElement ae, DefaultCacheConfig cachingConfig, HCacheEvict ann) {
//        HCacheEvictOperation.Builder builder = new HCacheEvictOperation.Builder();
//        builder.
//    }
//
//    private CacheOperation parseCacheableAnnotation(AnnotatedElement ae, DefaultCacheConfig defaultConfig, HCacheable cacheable) {
//        HCacheableOperation.Builder builder = new HCacheableOperation.Builder();
//        builder.setName(ae.toString());
//        builder.setCacheNames(cacheable.cacheNames());
//        builder.setCacheField(cacheable.cacheField());
//        builder.setCondition(cacheable.condition());
//        builder.setSync(cacheable.sync());
//        builder.setUnless(cacheable.unless());
//        builder.setCacheManager(cacheable.cacheManager());
//        builder.setCacheResolver(cacheable.cacheResolver());
//        builder.setKey(cacheable.key());
//        builder.setKeyGenerator(cacheable.keyGenerator());
//        defaultConfig.apply(builder);
//        HCacheableOperation op = (HCacheableOperation)builder.build();
//        validateCacheOperation(ae, op);
//
//        return op;
//    }
//
//    private void validateCacheOperation(AnnotatedElement ae, HCacheableOperation op) {
//        if (StringUtils.hasText(op.getKey()) && StringUtils.hasText(op.getKeyGenerator())) {
//            throw new IllegalStateException("Invalid cache annotation configuration on '" +
//                    ae.toString() + "'. Both 'key' and 'keyGenerator' attributes have been set. " +
//                    "These attributes are mutually exclusive: either set the SpEL expression used to" +
//                    "compute the key at runtime or set the name of the KeyGenerator bean to use.");
//        }
//        if (StringUtils.hasText(op.getCacheManager()) && StringUtils.hasText(op.getCacheResolver())) {
//            throw new IllegalStateException("Invalid cache annotation configuration on '" +
//                    ae.toString() + "'. Both 'cacheManager' and 'cacheResolver' attributes have been set. " +
//                    "These attributes are mutually exclusive: the cache manager is used to configure a" +
//                    "default cache resolver if none is set. If a cache resolver is set, the cache manager" +
//                    "won't be used.");
//        }
//    }
//
//
//    protected class DefaultCacheConfig {
//        private final Class<?> target;
//
//        @Nullable
//        private String[] cacheNames;
//
//        @Nullable
//        private String keyGenerator;
//
//        @Nullable
//        private String cacheManager;
//
//        @Nullable
//        private String cacheResolver;
//
//        private boolean initialized = false;
//
//        public DefaultCacheConfig(Class<?> targetClazz) {
//            target = targetClazz;
//        }
//
//        public void apply(HCacheableOperation.Builder builder) {
//            if (!this.initialized) {
//                CacheConfig annotation = AnnotatedElementUtils.findMergedAnnotation(this.target, CacheConfig.class);
//                if (annotation != null) {
//                    this.cacheManager = annotation.cacheManager();
//                    this.cacheNames = annotation.cacheNames();
//                    this.cacheResolver = annotation.cacheResolver();
//                    this.keyGenerator = annotation.keyGenerator();
//                }
//                this.initialized = true;
//            }
//            if (this.cacheNames != null && builder.getCacheNames().isEmpty()) {
//                builder.setCacheNames(this.cacheNames);
//            }
//            if (!StringUtils.hasText(builder.getKey()) && !StringUtils.hasText(builder.getKeyGenerator())
//                    && StringUtils.hasText(this.keyGenerator)) {
//                builder.setKeyGenerator(this.keyGenerator);
//            }
//            if (StringUtils.hasText(builder.getCacheManager()) || StringUtils.hasText(builder.getCacheResolver())) {
//
//            }else if(StringUtils.hasText(this.cacheResolver)){
//                builder.setCacheResolver(this.cacheResolver);
//            }else if(StringUtils.hasText(this.cacheManager)){
//                builder.setCacheManager(this.cacheManager);
//            }
//        }
//    }
}
