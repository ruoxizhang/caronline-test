package com.ruoxi.caronline.controller;

import com.ruoxi.caronline.data.UrlData;
import com.ruoxi.caronline.service.UrlService;
import com.ruoxi.caronline.utils.UrlProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateShortURL {

    private final UrlService urlService;
    private final UrlProps urlProps;

    @Autowired
    public CreateShortURL(UrlService urlService, UrlProps urlProps) {
        this.urlService = urlService;
        this.urlProps = urlProps;
    }

    @PostMapping("/shorturl")
    public String createShortURL(@RequestBody UrlData longURL) {
        return urlProps.getDomainName() + urlService.createNewUrl(longURL.getLongUrl()).getShortUrlCode();
    }
}
