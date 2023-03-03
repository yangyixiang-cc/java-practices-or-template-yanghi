package com.example.sstest.reistest;

import com.example.sstest.bean.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;

@SpringBootTest
public class Test1 {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Menu menu = new Menu();
        menu.setMenuName("系统管理");
        valueOperations.set("menu", menu);

    }

    @Test
    public void test2(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Menu menu = (Menu) valueOperations.get("menu");
        System.out.println(menu.getMenuName());
    }

}

