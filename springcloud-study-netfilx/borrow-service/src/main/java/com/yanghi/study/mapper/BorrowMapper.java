package com.yanghi.study.mapper;

import com.yanghi.study.bean.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 泗安
 */
@Mapper
public interface BorrowMapper {

    /**
     * 通过用户ID查询其借阅记录
     * @param uid 用户ID
     * @return 借阅记录
     */
    List<Borrow> getBorrowsByUid(int uid);

    /**
     * 通过书籍ID查询书籍的借阅记录
     * @param bid 书籍ID
     * @return 借阅记录
     */
    List<Borrow> getBorrowsByBid(int bid);

    /**
     * 查询用户借阅指定书籍的借阅记录
     * @param uid
     * @param bid
     * @return
     */
    Borrow getBorrowByUidAndBid(int uid, int bid);

}
