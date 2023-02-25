package com.yanghi.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author 泗安
 */
@EnableResourceServer
@SpringBootApplication
public class BookServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceBootApplication.class, args);
    }

}
