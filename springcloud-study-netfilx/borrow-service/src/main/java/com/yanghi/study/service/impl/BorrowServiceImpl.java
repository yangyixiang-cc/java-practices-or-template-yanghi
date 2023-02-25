package com.yanghi.study.service.impl;

import com.yanghi.study.bean.Book;
import com.yanghi.study.bean.Borrow;
import com.yanghi.study.bean.User;
import com.yanghi.study.bean.UserBorrowDetail;
import com.yanghi.study.client.BookClient;
import com.yanghi.study.client.UserClient;
import com.yanghi.study.mapper.BorrowMapper;
import com.yanghi.study.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
    private UserClient userClient;

    @Resource
    private BookClient bookClient;

    /**
     * 通过用户ID获取用户详细借阅信息 -> 用户和借阅图书详细信息
     * 需调用用户服务和图书服务
     * @param uid 用户ID
     * @return 用户详细借阅信息
     */
    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        //通过uid拿到借阅关系信息
        List<Borrow> borrowsByUid = borrowMapper.getBorrowsByUid(uid);
        //请求user-service获取用户信息
        User user = userClient.getUserById(uid);
//        User user = restTemplate.getForObject("http://userservice/user/" + uid, User.class);
        List<Book> bookList = borrowsByUid.stream()
                //通过用户借阅关系中的图书ID向 book-service 发起请求获取其所借阅的图书列表
                .map(
//                        b -> restTemplate.getForObject("http://bookservice/book/" + b.getBid(), Book.class)
                     b -> bookClient.getBookById(b.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }
}
