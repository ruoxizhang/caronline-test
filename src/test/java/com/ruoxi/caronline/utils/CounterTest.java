package com.ruoxi.caronline.utils;

import com.ruoxi.caronline.repositories.UrlRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Counter.class})
public class CounterTest {

    @Autowired
    private Counter counter;

    @MockBean
    private UrlRepo urlRepo;

    @Before
    public void init() {
        when(urlRepo.findMaxIndex()).thenReturn(Long.valueOf(10));
        counter.loadCounter();
    }

    @Test
    public void testCounter() {
        assertThat(counter.getCounter().getAndIncrement()).isEqualTo(10);
        assertThat(counter.getCounter().getAndIncrement()).isEqualTo(11);
    }
}