package com.yanghi.study.controller;

import com.yanghi.study.bean.Book;
import com.yanghi.study.bean.LoginUser;
import com.yanghi.study.service.BookService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 泗安
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/{bid}")
    public Book findUserById(@PathVariable("bid") int bid){
        return bookService.getBookById(bid);
    }



}
