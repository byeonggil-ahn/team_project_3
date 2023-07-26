package com.example.perfume01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
@MapperScan(value = {"mapperInterface"})
public class Perfume01Application {

    public static void main(String[] args) {
        SpringApplication.run(Perfume01Application.class, args);
    }

}
