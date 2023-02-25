package com.yanghi.study.service.impl;

import com.yanghi.study.bean.User;
import com.yanghi.study.mapper.UserMapper;
import com.yanghi.study.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 泗安
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }

}
