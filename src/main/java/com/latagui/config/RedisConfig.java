package com.latagui.config;



import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author snoopy
 * @date 2020/1/14 0014 23:07
 * @email 77912204@qq.com
 * @remark
 */

@Configuration
@EnableCaching
public class RedisConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Bean
    public CacheManager cacheManager(){
       // RedisCacheManager redisCacheManager = new RedisCacheManager();

        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(Duration.ofMinutes(30)),
                this.getRedisCacheConfiguration()
        );
    }
    /**
     * 自定义缓存过期时间
     * @return
     */
    private Map<String,RedisCacheConfiguration> getRedisCacheConfiguration(){
            Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(1);
            redisCacheConfigurationMap.put("key",RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1)).prefixKeysWith("snopy_"));
            return redisCacheConfigurationMap;
        }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis主键的序列化形式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
