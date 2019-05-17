package com.ruoxi.caronline.service;


import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.repositories.UrlRepo;

public class WriteUrlEntityToDBTask implements Runnable {

    private final UrlEntity urlEntity;
    private final UrlRepo urlRepo;

    public WriteUrlEntityToDBTask(UrlEntity urlEntity, UrlRepo urlRepo) {
        this.urlEntity = urlEntity;
        this.urlRepo = urlRepo;
    }

    @Override
    public void run() {
        urlRepo.saveUrlData(urlEntity);
    }
}
