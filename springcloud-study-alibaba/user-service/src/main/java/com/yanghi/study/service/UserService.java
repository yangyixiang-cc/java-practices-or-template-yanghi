package com.yanghi.study.service;

import com.yanghi.study.bean.User;

/**
 * @author yanghi
 */
public interface UserService {

    User getUserById(int uid);

    int getRemain(int uid);

    boolean userBorrow(int uid);


}
