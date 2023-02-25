package com.yanghi.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanghi.study.bean.LoginUser;
import com.yanghi.study.bean.ResponseResult;
import com.yanghi.study.bean.User;
import com.yanghi.study.mapper.UserMapper;
import com.yanghi.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User getUserOneByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",username);
        return this.getOne(userQueryWrapper);
    }

    @Override
    public boolean addUserOne(String username,String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUserName(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return this.save(user);
    }

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user",loginUser.getUser());
        return new ResponseResult(200,"登录成功",stringObjectHashMap);
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        Long id = principal.getUser().getId();
        return new ResponseResult(200,"注销成功");
    }
}
