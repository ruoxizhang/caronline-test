package com.ruoxi.caronline.controller;

import com.ruoxi.caronline.data.UrlData;
import com.ruoxi.caronline.service.UrlService;
import com.ruoxi.caronline.utils.UrlProps;
import org.apache.commons.validator.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateShortURL {

	private static Logger log = LoggerFactory.getLogger(CreateShortURL.class);
	private final UrlService urlService;
	private final UrlProps urlProps;
	private final UrlValidator urlValidator;

	@Autowired
	public CreateShortURL(UrlService urlService, UrlProps urlProps, UrlValidator urlValidator) {
		this.urlService = urlService;
		this.urlProps = urlProps;
		this.urlValidator = urlValidator;
	}

	@PostMapping("/shorturl")
	public String createShortURL(@RequestBody UrlData longURL) {
		if (!validateUrl(longURL)) {
			log.debug("There is a request with invalid url {}", longURL.getLongUrl());
			return "This is not a valid url";
		}
		return urlProps.getDomainName() + urlService.createNewUrl(longURL.getLongUrl()).getShortUrlCode();
	}

	private boolean validateUrl(UrlData longUrl) {
		if (longUrl == null) {
			log.debug("There is a request with an empty url");
			return false;
		}
		return urlValidator.isValid(longUrl.getLongUrl());
	}
}
