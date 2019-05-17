package com.ruoxi.caronline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @GetMapping("/health")
    public String health() {
        return "Url shorten application is running!";
    }
}
