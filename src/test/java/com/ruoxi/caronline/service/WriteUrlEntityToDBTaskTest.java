package com.ruoxi.caronline.service;

import com.ruoxi.caronline.data.UrlEntity;
import com.ruoxi.caronline.repositories.UrlRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class WriteUrlEntityToDBTaskTest {

    private UrlEntity urlEntity;

    @MockBean
    private UrlRepo urlRepo;

    @Before
    public void init() {
        urlEntity = new UrlEntity();
        urlEntity.setId(1);
        urlEntity.setShortUrlCode("0");
        urlEntity.setLongUrl("10");
        doNothing().when(urlRepo).saveUrlData(urlEntity);
    }

    @Test
    public void test() {
        WriteUrlEntityToDBTask writeUrlEntityToDBTask = new WriteUrlEntityToDBTask(urlEntity, urlRepo);
        writeUrlEntityToDBTask.run();
        verify(urlRepo, times(1)).saveUrlData(urlEntity);
    }
}