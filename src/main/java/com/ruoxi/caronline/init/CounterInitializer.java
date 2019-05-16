package com.ruoxi.caronline.init;

import com.ruoxi.caronline.utils.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CounterInitializer {

	private volatile AtomicBoolean isInit = new AtomicBoolean(false);

	@Autowired
	Counter counter;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!isInit.compareAndSet(false, true)) {
			return;
		}

		init();
	}

	private void init() {
		counter.loadCounter();
	}
}
