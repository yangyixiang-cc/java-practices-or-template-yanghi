package com.yanghi.study.client;

import com.yanghi.study.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 泗安
 */
@FeignClient(value = "userservice")
public interface UserClient {

    /**
     * 根据用户ID获取用户信息
     *
     * @param uid 用户ID
     * @return 用户信息
     */
    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") int uid);

    @RequestMapping("/user/remain/{uid}")
    int userRemain(@PathVariable("uid") int uid);

    @RequestMapping("/user/borrow/{uid}")
    boolean userBorrow(@PathVariable("uid") int uid);

}
