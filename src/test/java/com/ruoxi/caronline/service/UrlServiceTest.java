package com.ruoxi.caronline.service;

import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.repositories.UrlRepo;
import com.ruoxi.caronline.utils.Counter;
import com.ruoxi.caronline.utils.MainCache;
import com.ruoxi.caronline.utils.UrlExecutorPool;
import com.ruoxi.caronline.utils.UrlProps;
import io.seruco.encoding.base62.Base62;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Base62.class, UrlProps.class, UrlDataCreator.class,
        UrlExecutorPool.class, MainCache.class, UrlService.class})
public class UrlServiceTest {

    private long id = 10;
    private String longUrl = "www.longurl.com";
    private String shortUrlCode = "18X7tZKzczNTo8";

    @MockBean
    private UrlRepo urlRepo;

    @Autowired
    private UrlService urlService;

    @MockBean
    private Counter counter;

    @Before
    public void init() {
        AtomicLong mockCounter = new AtomicLong(10);
        when(counter.getCounter()).thenReturn(mockCounter);
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(id);
        urlEntity.setShortUrlCode(shortUrlCode);
        urlEntity.setLongUrl(longUrl);
        doNothing().when(urlRepo).saveUrlData(urlEntity);
    }

    @Test
    public void test() throws ExecutionException {
        urlService.createNewUrl(longUrl);
        assertThat(urlService.getLongUrl(shortUrlCode)).isEqualTo(longUrl);
    }
}