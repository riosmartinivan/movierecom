package com.example.movierecom.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.movierecom.services")
@Getter
@Setter
public class ServiceURLsProperties {
    private String tmdbServiceURL;
    private String netflixServiceURL;
    private String imdbServiceURL;
}
