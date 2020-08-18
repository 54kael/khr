package com.kael.hr.util;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/18 0018
 */
public class RedisUtil {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    public  void deletedKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
