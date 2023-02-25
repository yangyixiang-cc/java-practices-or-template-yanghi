package com.yanghi.study.handler;

import com.alibaba.fastjson.JSON;
import com.yanghi.study.bean.ResponseResult;
import com.yanghi.study.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理授权异常
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "你的权限不足");
        String s = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,s);
    }
}
