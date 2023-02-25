package com.yanghi.study.controller;

import com.yanghi.study.bean.Book;
import com.yanghi.study.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public Book findUserById(@PathVariable("bid") int bid, @RequestHeader("Test") String testHeader){
        System.out.println(testHeader);
        return bookService.getBookById(bid);
    }

}
