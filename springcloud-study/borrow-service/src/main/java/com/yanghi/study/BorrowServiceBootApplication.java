package com.yanghi.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author 泗安
 */
@EnableFeignClients
@EnableResourceServer
@SpringBootApplication
public class BorrowServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowServiceBootApplication.class, args);
    }
}
