package com.yanghi.study.controller;

import com.yanghi.study.bean.UserBorrowDetail;
import com.yanghi.study.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 泗安
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    @RequestMapping("/{uid}")
    public UserBorrowDetail findUserBorrowDetailByUid(@PathVariable("uid") int uid){
        return borrowService.getUserBorrowDetailByUid(uid);
    }

}
