package com.yanghi.study;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 泗安
 */
@EnableAutoDataSourceProxy
@SpringBootApplication
public class UserServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootApplication.class, args);
    }
}
