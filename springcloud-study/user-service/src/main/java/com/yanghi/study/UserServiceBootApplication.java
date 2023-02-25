package com.yanghi.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author 泗安
 */
@EnableResourceServer
@SpringBootApplication
public class UserServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootApplication.class, args);
    }
}
