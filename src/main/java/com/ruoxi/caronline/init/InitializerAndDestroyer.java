package com.ruoxi.caronline.init;

import com.ruoxi.caronline.utils.Counter;
import com.ruoxi.caronline.utils.UrlExecutorPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class InitializerAndDestroyer {

    private final Counter counter;
    private final UrlExecutorPool urlExecutorPool;

    private volatile AtomicBoolean isInit = new AtomicBoolean(false);
    private volatile AtomicBoolean isThreadPoolShutdown = new AtomicBoolean(false);

    @Autowired
    public InitializerAndDestroyer(Counter counter, UrlExecutorPool urlExecutorPool) {
        this.counter = counter;
        this.urlExecutorPool = urlExecutorPool;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!isInit.compareAndSet(false, true)) {
            return;
        }
        init();
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        if (!isThreadPoolShutdown.compareAndSet(false, true)) {
            return;
        }
        urlExecutorPool.getExecutorService().shutdown();
    }


    private void init() {
        counter.loadCounter();
    }
}
