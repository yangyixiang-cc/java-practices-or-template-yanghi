package com.yanghi.study.service;

import com.yanghi.study.bean.UserBorrowDetail;

/**
 * @author 泗安
 */
public interface BorrowService {

    /**
     * 通过用户ID获取用户详细借阅信息
     * @param uid 用户ID
     * @return 用户借阅详细信息
     */
    UserBorrowDetail getUserBorrowDetailByUid(int uid);


}
