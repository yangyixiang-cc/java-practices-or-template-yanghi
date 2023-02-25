package com.yanghi.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanghi.study.bean.ResponseResult;
import com.yanghi.study.bean.User;

public interface UserService extends IService<User> {

    User getUserOneByUsername(String username);


    boolean addUserOne(String username,String password);

    ResponseResult login(User user);

    ResponseResult logout();

}
