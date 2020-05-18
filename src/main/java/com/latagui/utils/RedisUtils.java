package com.latagui.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author snoopy
 * @date 2020/1/15 0015 23:52
 * @email 77912204@qq.com
 * @remark redis操作工具
 */
@Component
public class RedisUtils {


    @Autowired
    private RedisTemplate<String, Object>   redisTemplate;
    @Autowired
    private HashOperations<String,String,Object> hashOperations;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private ListOperations<String, Object>  listOperations;
    @Autowired
    private SetOperations<String, Object>   setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    //设置默认过期时间
    public static final int DEFAULT_EXPIRE  = 60 * 60 * 24;
    //不设置过期
    public static final int NOT_EXPIRE      = -1;
    /**
     * 设置值与过期时间
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key,Object value, long expire) {
        valueOperations.set(key, JsonUtils.toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置值，默认过期时间1天
     * @param key
     * @param value
     */
    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }



}
