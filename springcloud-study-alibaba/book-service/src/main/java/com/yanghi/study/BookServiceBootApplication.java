package com.yanghi.study;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 泗安
 */
@EnableAutoDataSourceProxy
@SpringBootApplication
public class BookServiceBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceBootApplication.class, args);
    }

}
