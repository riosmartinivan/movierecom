package com.example.movierecom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${build.version}")
    private String version;

    @Bean
    public Docket productApi() {
        Contact contact = new Contact(
            "Martin Ivan Rios",
            "https://www.linkedin.com/in/ivr2132",
            "rios.martinivan@gmail.com");

        ApiInfo apiInfo = new ApiInfo(
            "Movierecom Server API",
            "This is the best stuff since sliced bread - API",
            version,
            "https://www.linkedin.com/in/ivr2132",
            contact,
            "Copyright",
            "https://www.linkedin.com/in/ivr2132",
            new ArrayList<>());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .pathMapping("/")
            .apiInfo(ApiInfo.DEFAULT)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(Pageable.class)
            .ignoredParameterTypes(java.sql.Date.class)
            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
            .useDefaultResponseMessages(false);

        docket = docket.select().apis(RequestHandlerSelectors
            .basePackage("com.example.movierecom"))
            .paths(PathSelectors.any())
            .build();
        return docket;
    }
}
