package com.ruoxi.caronline.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {UrlExecutorPool.class, UrlProps.class})
@RunWith(SpringRunner.class)
public class UrlExecutorPoolTest {

    @Autowired
    private UrlExecutorPool urlExecutorPool;

    @Test
    public void testExecutorPool() {
        assertThat(urlExecutorPool.getExecutorService()).isNotNull();
    }
}