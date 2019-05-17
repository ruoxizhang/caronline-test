package com.ruoxi.caronline.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "shorturl.config")
@Data
public class UrlProps {
    private String domainName = "localhost:8089/";
    private Integer threadPoolSize = 99;
}

