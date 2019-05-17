package com.ruoxi.caronline.service;

import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.utils.Counter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UrlDataCreator.class})
public class UrlDataCreatorTest {
    private long id = 10;
    private String longUrl = "www.longurl.com";
    private String shortUrlCode = "18X7tZKzczNTo8";
    private UrlEntity urlEntity;

    @Autowired
    private UrlDataCreator urlDataCreator;

    @MockBean
    private Counter counter;

    @Before
    public void init() {
        AtomicLong mockCounter = new AtomicLong(10);
        when(counter.getCounter()).thenReturn(mockCounter);
        urlEntity = new UrlEntity();
        urlEntity.setId(id);
        urlEntity.setShortUrlCode(shortUrlCode);
        urlEntity.setLongUrl(longUrl);
    }

    @Test
    public void test() {
        assertThat(urlDataCreator.createNewUrlData(longUrl)).isEqualTo(urlEntity);
    }

}