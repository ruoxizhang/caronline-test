package com.ruoxi.caronline.service;

import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.utils.Counter;
import io.seruco.encoding.base62.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlDataCreator {
    private final Counter counter;

    private Base62 base62 = Base62.createInstance();

    @Autowired
    public UrlDataCreator(Counter counter) {
        this.counter = counter;
    }

    UrlEntity createNewUrlData(String longUrl) {
        long currentCounterNumber = counter.getCounter().getAndIncrement();
        String shortUrlCode = generateShortUrlCode(currentCounterNumber);
        return createUrlData(longUrl, currentCounterNumber, shortUrlCode);
    }

    private UrlEntity createUrlData(String longUrl, long currentCounterNumber, String shortUrl) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(currentCounterNumber);
        urlEntity.setLongUrl(longUrl);
        urlEntity.setShortUrlCode(shortUrl);
        return urlEntity;
    }

    private String generateShortUrlCode(long currentCounterNumber) {
        String numberString = String.format("%010d", currentCounterNumber);
        return new String(base62.encode(numberString.getBytes()));
    }
}
