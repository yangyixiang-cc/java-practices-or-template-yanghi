package com.yanghi.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 泗安
 */
@SpringBootApplication
@EnableFeignClients
public class BorrowServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowServiceBootApplication.class, args);
    }
}
