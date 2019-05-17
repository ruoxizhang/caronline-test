package com.ruoxi.caronline.controller;

import com.google.common.cache.CacheLoader;
import com.ruoxi.caronline.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class GetLongUrl {

    private final UrlService urlService;

    @Autowired
    public GetLongUrl(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrlCode}")
    public String getLongUrl(@PathVariable String shortUrlCode) {
        try {
            return urlService.getLongUrl(shortUrlCode);
        } catch (CacheLoader.InvalidCacheLoadException e) {
            return "The url is not found!";
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Something went wrong!";
    }
}
