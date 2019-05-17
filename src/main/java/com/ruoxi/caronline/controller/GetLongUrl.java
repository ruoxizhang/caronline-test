package com.ruoxi.caronline.controller;

import com.google.common.cache.CacheLoader;
import com.google.common.net.HttpHeaders;
import com.ruoxi.caronline.exception.NotFoundException;
import com.ruoxi.caronline.service.UrlService;
import com.ruoxi.caronline.utils.UrlProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@RestController
public class GetLongUrl {

	private static Logger log = LoggerFactory.getLogger(GetLongUrl.class);
	private final UrlService urlService;

	@Autowired
	public GetLongUrl(UrlService urlService, UrlProps urlProps) {
		this.urlService = urlService;
	}

	@GetMapping("/{shortUrlCode}")
	public RedirectView getLongUrl(@PathVariable String shortUrlCode, HttpServletResponse httpServletResponse) {
		try {
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(urlService.getLongUrl(shortUrlCode));
			return redirectView;
		} catch (CacheLoader.InvalidCacheLoadException e) {
			log.debug("url not found");
		} catch (ExecutionException e) {
			log.debug("something wrong");
		}
		throw new NotFoundException();
	}
}
