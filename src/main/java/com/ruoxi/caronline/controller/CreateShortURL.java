package com.ruoxi.caronline.controller;

import com.ruoxi.caronline.data.LongUrlData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateShortURL {

	@PostMapping("/shorturl")
	public String createShortURL(@RequestBody LongUrlData longURL) {
		return longURL.getLongUrl();
	}
}
