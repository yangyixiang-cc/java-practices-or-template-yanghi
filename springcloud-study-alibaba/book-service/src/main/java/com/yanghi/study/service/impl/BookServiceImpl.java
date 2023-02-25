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

    @Override
    public int getRemain(int bid) {
        return bookMapper.getRemain(bid);
    }

    @Override
    public boolean bookBorrow(int bid) {
        boolean result = false;
        int remain = bookMapper.getRemain(bid);
        if (remain >= 1){
            result = bookMapper.setRemain(bid, remain - 1) > 0;
        }
        return result;
    }

}
