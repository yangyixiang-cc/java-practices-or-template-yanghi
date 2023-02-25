package com.yanghi.study.mapper;

import com.yanghi.study.bean.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 泗安
 */
@Mapper
public interface BookMapper {

    Book getBookById(int bid);

    int getRemain(int bit);

    int setRemain(int bid, int count);

}
