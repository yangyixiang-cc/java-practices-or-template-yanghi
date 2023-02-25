package com.yanghi.study.controller;

import com.yanghi.study.bean.ResponseResult;
import com.yanghi.study.bean.User;
import com.yanghi.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(
            User user
    ){
        return userService.login(user);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')") //判断访问者是否有test权限,权限就是一个个名称，如 admin 管理者 user 普通用户等
    public String hello(){
        return "hello";
    }

    @GetMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

    @GetMapping("/test")
    public String test(){
        userService.addUserOne("test", "123456");
        return "成功";
    }

}
