package com.yanghi.study.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yanghi.study.bean.Book;
import com.yanghi.study.bean.Borrow;
import com.yanghi.study.bean.User;
import com.yanghi.study.bean.UserBorrowDetail;
import com.yanghi.study.client.BookClient;
import com.yanghi.study.client.UserClient;
import com.yanghi.study.mapper.BorrowMapper;
import com.yanghi.study.service.BorrowService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 泗安
 */
@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private BookClient bookClient;

    @Resource
    private UserClient userClient;

    /**
     * 通过用户ID获取用户详细借阅信息 -> 用户和借阅图书详细信息
     * 需调用用户服务和图书服务
     * @param uid 用户ID
     * @return 用户详细借阅信息
     */
    //监控此方法，无论被那个接口执行都在监控范围内，这里给的value是自定义名称，这个注解可以加在任何方法上，
    @SentinelResource(value = "getBorrow")
    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        //通过uid拿到借阅关系信息
        List<Borrow> borrowsByUid = borrowMapper.getBorrowsByUid(uid);
        //请求user-service获取用户信息
        User user = userClient.getUserById(uid);
        List<Book> bookList = borrowsByUid.stream()
                //通过用户借阅关系中的图书ID向 book-service 发起请求获取其所借阅的图书列表
                .map(b -> bookClient.getBookById(b.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }

    public UserBorrowDetail blocked(int uid, BlockException e) {
        return new UserBorrowDetail(null, Collections.emptyList());
    }

    @GlobalTransactional
    @Override
    public boolean doBorrow(int uid, int bid) {
        //判断
        if(userClient.userRemain(uid) < 1){
            throw new RuntimeException("用户借阅量已满");
        }
        if(bookClient.bookRemain(bid) < 1) {
            throw new RuntimeException("图书可借阅量为0");
        }
        //将图书数量-1
        if(!bookClient.bookBorrow(bid)){
            throw new RuntimeException("借阅图书时出现错误！");
        }
        //先查询用户是否借阅此书籍
        if(borrowMapper.getBorrowByUidAndBid(uid, bid) != null){
            throw new RuntimeException("此书籍已经被此用户借阅了");
        }
        //然后添加借阅记录
        if(borrowMapper.addBorrwByUidAndBid(uid, bid) <= 0){
            throw new RuntimeException("在录入借阅信息时出现错误");
        }
        //将用户数量-1
        if(!userClient.userBorrow(uid)){
            throw new RuntimeException("在借阅时出现错误");
        }

        return true;
    }

}