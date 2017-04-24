package com.imlsz.service;

/**
 * Created by lsz on 2017/4/24.
 */
public interface StoreService {
    void save(String shortCode,String url);
    long incrAndGet();
    String get(String shortCode);
}
