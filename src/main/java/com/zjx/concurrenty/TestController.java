package com.zjx.concurrenty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/12/28.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
