package com.ruoxi.caronline.service;

import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.repositories.UrlRepo;
import com.ruoxi.caronline.utils.MainCache;
import com.ruoxi.caronline.utils.UrlExecutorPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UrlService {
    private final UrlDataCreator urlDataCreator;

    private final UrlExecutorPool urlExecutorPool;

    private final MainCache cache;

    private final UrlRepo urlRepo;

    @Autowired
    public UrlService(UrlDataCreator urlDataCreator, UrlExecutorPool urlExecutorPool, MainCache cache, UrlRepo urlRepo) {
        this.urlDataCreator = urlDataCreator;
        this.urlExecutorPool = urlExecutorPool;
        this.cache = cache;
        this.urlRepo = urlRepo;
    }

    public UrlEntity createNewUrl(String longUrl) {
        UrlEntity urlEntity = urlDataCreator.createNewUrlData(longUrl);
        WriteUrlEntityToDBTask writeUrlEntityToDBTask = new WriteUrlEntityToDBTask(urlEntity, urlRepo);
        urlExecutorPool.getExecutorService().submit(writeUrlEntityToDBTask);
        cache.getCache().put(urlEntity.getShortUrlCode(), urlEntity.getLongUrl());
        return urlEntity;
    }

    public String getLongUrl(String shortUrlCode) throws ExecutionException {
        return cache.getCache().get(shortUrlCode);
    }
}
