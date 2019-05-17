package com.ruoxi.caronline.utils;

import com.ruoxi.caronline.CaronlineApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = CaronlineApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
@RunWith(SpringRunner.class)
public class UrlPropsTest {
    @Autowired
    private UrlProps urlProps;

    private String domainName = "localhost:8080/";
    private Integer threadPoolSize = 100;

    @Test
    public void testProps() {
        assertThat(urlProps.getDomainName()).isEqualTo(domainName);
        assertThat(urlProps.getThreadPoolSize()).isEqualTo(threadPoolSize);
    }
}