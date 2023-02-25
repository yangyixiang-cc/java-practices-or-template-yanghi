package com.yanghi.study.client.impl;

import com.yanghi.study.bean.User;

public class UserClientImpl{

    public User getUserById(int uid) {
        User user = new User();
        user.setName("我是替代方案");
        return user;
    }

}
