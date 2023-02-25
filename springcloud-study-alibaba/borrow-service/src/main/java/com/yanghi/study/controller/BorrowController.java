package com.yanghi.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSONObject;
import com.yanghi.study.bean.UserBorrowDetail;
import com.yanghi.study.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;

/**
 * @author 泗安
 */
@RestController
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    @RequestMapping("borrow/{uid}")
    @SentinelResource(value = "borrow", blockHandler = "borrowHandler")
    public UserBorrowDetail findUserBorrowDetailByUid(@PathVariable("uid") int uid){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return borrowService.getUserBorrowDetailByUid(uid);
    }


    public UserBorrowDetail borrowHandler(int uid){
        return new UserBorrowDetail(null, Collections.emptyList());
    }


    @RequestMapping("/test")
    @SentinelResource(value = "test",
            blockHandler = "blockedHandler",
            fallback = "except",    //fallback指定出现异常时的替代方案
            exceptionsToIgnore = IOException.class)  //忽略那些异常，也就是说这些异常出现时不使用替代方案
    String test(){
        return  1/0 + "";
    }

    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
    String except(Throwable t){
        return t.getMessage();
    }

    @RequestMapping("/test1")
    @SentinelResource(value = "test1")
    String test1(@RequestParam(value = "a", required = false) String a,
                 @RequestParam(value = "b", required = false) String b,
                 @RequestParam(value = "c", required = false) String c) {
        return "a：" + a + "b：" + b + "c：" + c;
    }

    public String blockedHandler(String a, String b, String c){
        return "限流";
    }

    public String exceptionHandler(String a, String b, String c, Throwable t){
        return "热点参数限流：" + t.getLocalizedMessage() + "：" + t.getMessage() ;
    }

//    public JSONObject exceptionHandler(String a, String b, String c, Throwable t){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code", 403);
//        jsonObject.put("success", false);
//        jsonObject.put("msg", "你的请求频率过快，请稍后再试！超过设定阈值");
//        jsonObject.put("exception", t.getMessage());
//        return jsonObject;
//    }

    @RequestMapping("/borrow/take/{uid}/{bid}")
    public JSONObject doBorrow(@PathVariable("uid") int uid,
                               @PathVariable("bid") int bid){
        borrowService.doBorrow(uid, bid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "200");
        jsonObject.put("success", false);
        jsonObject.put("msg", "借阅成功");
        return jsonObject;
    }

}
