package com.yanghi.study.client;

import com.yanghi.study.bean.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 泗安
 */
@FeignClient("bookservice")
public interface BookClient {

    /**
     * 根据图书ID获取图书信息
     *
     * @param bid 图书ID
     * @return 图书信息
     */
    @RequestMapping("/book/{bid}")
    Book getBookById(@PathVariable("bid") int bid);

    @RequestMapping("/book/remain/{bid}")
    int bookRemain(@PathVariable("bid") int bid);

    @RequestMapping("/book/borrow/{bid}")
    boolean bookBorrow(@PathVariable("bid") int bid);

}
