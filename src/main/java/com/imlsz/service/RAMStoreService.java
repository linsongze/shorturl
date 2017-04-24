package com.imlsz.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lsz on 2017/4/24.
 */
@Service
public class RAMStoreService implements StoreService {
    private Map<String,String> storeMap = new HashMap<>();
    private AtomicLong atomicLong = new AtomicLong(0);
    @Override
    public void save(String shortCode, String url) {
        storeMap.put(shortCode,url);
    }

    @Override
    public long incrAndGet() {
        return atomicLong.incrementAndGet();
    }

    @Override
    public String get(String shortCode) {
        return storeMap.get(shortCode);
    }
}
