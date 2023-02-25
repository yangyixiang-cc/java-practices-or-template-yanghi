package com.yanghi.study.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 泗安
 */
@RestController
@RequestMapping("/return")
public class ReturnTipController {

    @RequestMapping("/blocked")
    public JSONObject blocked(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 403);
        jsonObject.put("success", false);
        jsonObject.put("msg", "你的请求频率过快，请稍后再试！");
        return jsonObject;
    }

}
