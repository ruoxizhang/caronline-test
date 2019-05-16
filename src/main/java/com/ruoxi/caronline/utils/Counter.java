package com.ruoxi.caronline.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Data
@Component
public class Counter {

	AtomicLong counter;

	public void loadCounter() {

	}
}
