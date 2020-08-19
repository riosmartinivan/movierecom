package com.example.movierecom;

import com.example.movierecom.config.ServiceAPIKeys;
import com.example.movierecom.config.ServiceURLsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    ServiceURLsProperties.class,
    ServiceAPIKeys.class
})
public class MovierecomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovierecomApplication.class, args);
    }

}
