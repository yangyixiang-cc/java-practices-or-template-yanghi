package com.example.sstest.mapper;

import com.example.sstest.bean.User;
import com.example.sstest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        User test = userService.getUserOneByUsername("test");
        System.out.println(test);
    }

    @Test
    public void test2(){
        userService.addUserOne("yanghi","123456");
    }

}