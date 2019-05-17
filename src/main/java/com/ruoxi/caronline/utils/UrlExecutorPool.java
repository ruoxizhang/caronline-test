package com.ruoxi.caronline.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class UrlExecutorPool {
    private final UrlProps urlProps;

    private ExecutorService executorService;

    @Autowired
    public UrlExecutorPool(UrlProps urlProps) {
        this.urlProps = urlProps;
    }

    @PostConstruct
    private void initExecutorService() {
        executorService = Executors.newFixedThreadPool(urlProps.getThreadPoolSize());
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

}
