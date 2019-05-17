package com.ruoxi.caronline.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ruoxi.caronline.repositories.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;

@Component
public class MainCache {

    private final UrlRepo urlRepo;
    //assume 200 chars per entry, one char = 2byte, 5m entries using about 2GB mem
    private LoadingCache<String, String> cache = getNewLoadingCache(5000000);

    @Autowired
    public MainCache(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    private LoadingCache<String,String> getNewLoadingCache(long size) {
        return CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS)
                .concurrencyLevel(8)
                .maximumSize(size)
                .build(createCacheLoader());
    }

    private CacheLoader<String, String> createCacheLoader() {
        return new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return urlRepo.findLongUrl(key);
            }
        };
    }

    public LoadingCache<String, String> getCache() {
        return cache;
    }
}
