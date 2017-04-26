package com.imlsz.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lsz on 2017/4/26.
 */
@Service
public class RedisStoreService implements StoreService{
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void save(String shortCode, String url) {
        stringRedisTemplate.boundHashOps("ss").put(shortCode,url);
    }

    @Override
    public long incrAndGet() {
        return stringRedisTemplate.opsForValue().increment("sid",1);
    }

    @Override
    public String get(String shortCode) {
        return (String) stringRedisTemplate.boundHashOps("ss").get(shortCode);
    }
}
