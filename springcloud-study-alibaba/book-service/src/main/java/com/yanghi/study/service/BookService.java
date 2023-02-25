package com.yanghi.study.service;

import com.yanghi.study.bean.Book;

public interface BookService {


    Book getBookById(int bid);

    int getRemain(int bid);

    boolean bookBorrow(int bid);

}
