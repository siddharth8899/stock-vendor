package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class SmsSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsSenderApplication.class, args);
    }

    @Bean
    public Docket SwaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.internship"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "SMS Sending API",
                "This API is used to send sms to the vendors",
                "1.0",
                "Strictly for use of TCS",
                new Contact("Siddharth Jain", "www.tcs.com", "siddharth@tcs.com"),
                "License of API",
                "http://tcs",
                Collections.emptyList());
    }
}