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


    /**
     * 根据用户ID获取用户可借阅书籍数量
     * @param uid 用户id
     * @return 可借阅数量
     */
    int getUserBookRemain(int uid);


    /**
     * 根据用户ID更新用户的可借阅数量
     * @param uid 用户ID
     * @param count 可借阅数量
     * @return 返回值表明影响行数
     */
    int updateBookCount(int uid, int count);

}
