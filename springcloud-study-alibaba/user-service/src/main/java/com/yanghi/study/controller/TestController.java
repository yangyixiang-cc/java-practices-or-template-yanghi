package com.yanghi.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 泗安
 */
@RestController
@RefreshScope
public class TestController {

    //我们从配置文件中读取test.txt的字符串值，作为test接口的返回值
    @Value("${test.txt}")
    private String txt;

    @RequestMapping("/user/test")
    public String hotUpdateTest(){
        return txt;
    }

}
