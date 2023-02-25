package com.yanghi.study.mapper;

import com.yanghi.study.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 泗安
 */
@Mapper
public interface UserMapper{

    /**
     * 通过用户ID查询用户信息
     * @param uid 用户id
     * @return 用户信息
     */
    User getUserById(int uid);

}
