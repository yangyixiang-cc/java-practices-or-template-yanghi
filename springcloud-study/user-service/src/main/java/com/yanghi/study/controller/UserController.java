package com.yanghi.study.controller;

import com.yanghi.study.bean.User;
import com.yanghi.study.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 泗安
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/{uid}")
    public User findUserById(@PathVariable("uid") long uid){
        return userService.getUserById(uid);
    }

}
