package com.yanghi.study.service.impl;

import com.yanghi.study.bean.Book;
import com.yanghi.study.mapper.BookMapper;
import com.yanghi.study.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 泗安
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book getBookById(int bid) {
        return bookMapper.getBookById(bid);
    }

}
