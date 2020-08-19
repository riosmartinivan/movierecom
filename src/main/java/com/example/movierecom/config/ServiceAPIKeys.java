package com.example.movierecom.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.movierecom.key")
@Getter
@Setter
public class ServiceAPIKeys {
    private String tmdbAPIKey;
    private String netflixAPIKey;
    private String imdbAPIKey;
}
