package com.yanghi.study.handler;

import com.alibaba.fastjson.JSON;
import com.yanghi.study.bean.ResponseResult;
import com.yanghi.study.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理认证异常
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录");
        String s = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,s);
    }
}
