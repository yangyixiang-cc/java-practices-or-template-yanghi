package com.yanghi.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 泗安
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerBootApplication.class, args);
    }

}
