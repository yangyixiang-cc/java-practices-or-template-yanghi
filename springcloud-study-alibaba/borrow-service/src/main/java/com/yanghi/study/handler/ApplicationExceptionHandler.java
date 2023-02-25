package com.yanghi.study.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JSONObject handlerException(Exception e){ //e 接收异常信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 300);
        //获取异常信息，存放到 ResponseResult 的 msg 属性中
        String message = e.getMessage();
        jsonObject.put("msg", message);
        return jsonObject;
    }

}