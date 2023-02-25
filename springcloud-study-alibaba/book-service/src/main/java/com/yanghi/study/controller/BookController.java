package com.yanghi.study.controller;

import com.yanghi.study.bean.Book;
import com.yanghi.study.service.BookService;
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
        System.out.println("图书服务");
        return bookService.getBookById(bid);
    }

    @RequestMapping("/remain/{bid}")
    public int bookRemain(@PathVariable("bid") int bid){
        return bookService.getRemain(bid);
    }

    @RequestMapping("/borrow/{bid}")
    public boolean bookBorrow(@PathVariable("bid") int bid){
        return bookService.bookBorrow(bid);
    }


}
