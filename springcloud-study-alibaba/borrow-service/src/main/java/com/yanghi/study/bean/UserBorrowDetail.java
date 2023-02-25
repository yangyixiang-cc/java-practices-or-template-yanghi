package com.yanghi.study.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 泗安
 */
@Data
@AllArgsConstructor
public class UserBorrowDetail {

    User user;
    List<Book> bookList;

}