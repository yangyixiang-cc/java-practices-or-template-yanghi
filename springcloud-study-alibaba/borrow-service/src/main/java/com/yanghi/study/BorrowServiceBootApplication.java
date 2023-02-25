package com.yanghi.study;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 泗安
 */
@EnableAutoDataSourceProxy
@SpringBootApplication
@EnableFeignClients
public class BorrowServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowServiceBootApplication.class, args);
    }
}
