package com.example.demo.common.config;

import com.example.demo.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author yuhan
 */
@Configuration
@Slf4j
public class CacheConfig extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        return redisTemplate;
    }

    @Bean
    @Override
    @DependsOn("cacheManager")
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        log.error("cache error Handler");
        return new SimpleCacheErrorHandler();
    }

    @Bean
    @Override
    @Primary
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = null;

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
        return redisCacheManager;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator(){
        return (o,method,params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append("#");
            sb.append(method.getName()).append("(");
            for(int i=0; i< params.length; i++){
                sb.append(params[i].toString());
                if(i!= params.length-1){
                   sb.append(",");
                }
            }

            sb.append(")");
            log.info("keyGenerator=" + sb.toString());
            return sb.toString();
        };
    }
}
