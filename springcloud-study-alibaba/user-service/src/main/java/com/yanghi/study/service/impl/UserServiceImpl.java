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

    @Override
    public int getRemain(int uid) {
        return userMapper.getUserBookRemain(uid);
    }

    //更新用户可借阅数量
    @Override
    public boolean userBorrow(int uid) {
        boolean result = false;
        int userBookRemain = userMapper.getUserBookRemain(uid);
        if(userBookRemain >= 1 ){
            result = userMapper.updateBookCount(uid, userBookRemain - 1) > 0;
        }
        return result;
    }

}
