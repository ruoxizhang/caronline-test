package com.ruoxi.caronline.utils;


import com.google.common.cache.CacheLoader;
import com.ruoxi.caronline.repositories.UrlRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MainCache.class})
public class MainCacheTest {

    private final String urlCode0 = "12345";
    private final String urlCode1 = "22345";
    private final String urlCode2 = "13345";
    private final String urlCode3 = "12445";
    private final String urlCode4 = "12355";
    private final String urlCode5 = "32345";

    private final String longUrl0 = "0";
    private final String longUrl1 = "1";
    private final String longUrl2 = "2";
    private final String longUrl3 = "3";
    private final String longUrl4 = "4";
    private final String longUrl5 = "5";


    @MockBean
    private UrlRepo urlRepo;

    @Autowired
    private MainCache mainCache;

    @Before
    public void init() {
        when(urlRepo.findLongUrl("12345")).thenReturn("0");
        when(urlRepo.findLongUrl("22345")).thenReturn("1");
        when(urlRepo.findLongUrl("13345")).thenReturn("2");
        when(urlRepo.findLongUrl("12445")).thenReturn("3");
        when(urlRepo.findLongUrl("12355")).thenReturn("4");
        when(urlRepo.findLongUrl("32345")).thenReturn("5");
    }

    @Test
    public void test() throws ExecutionException {
        assertThat(mainCache.getCache().get(urlCode0)).isEqualTo(longUrl0);
        assertThat(mainCache.getCache().get(urlCode0)).isEqualTo(longUrl0);
        assertThat(mainCache.getCache().get(urlCode1)).isEqualTo(longUrl1);
        assertThat(mainCache.getCache().get(urlCode2)).isEqualTo(longUrl2);
        assertThat(mainCache.getCache().get(urlCode3)).isEqualTo(longUrl3);
        assertThat(mainCache.getCache().get(urlCode4)).isEqualTo(longUrl4);
        assertThat(mainCache.getCache().get(urlCode5)).isEqualTo(longUrl5);
        assertThat(mainCache.getCache().get(urlCode5)).isEqualTo(longUrl5);
    }

    @Test(expected = CacheLoader.InvalidCacheLoadException.class)
    public void testFail() throws ExecutionException {
        mainCache.getCache().get("test");
    }


}