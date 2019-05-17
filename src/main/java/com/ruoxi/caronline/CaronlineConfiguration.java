package com.ruoxi.caronline;

import org.apache.commons.validator.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaronlineConfiguration {

	@Bean
	public UrlValidator getUrlValidator() {
		return new UrlValidator();
	}
}
