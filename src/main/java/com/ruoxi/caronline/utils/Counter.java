package com.ruoxi.caronline.utils;

import com.ruoxi.caronline.repositories.UrlRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Data
@Component
public class Counter {

    AtomicLong counter;

    private final UrlRepo urlRepo;

    @Autowired
    public Counter(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public void loadCounter() {
        Long maxId = urlRepo.findMaxIndex();
        counter = new AtomicLong(maxId);
    }
}
