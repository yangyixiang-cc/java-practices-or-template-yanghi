package com.example.sstest.reistest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
public class Test1 {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        Set keys = redisTemplate.keys("*");
        keys.stream().forEach(o -> {
            System.out.println(o);
        });
    }

}
