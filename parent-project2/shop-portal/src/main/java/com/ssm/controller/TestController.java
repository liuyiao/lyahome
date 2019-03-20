package com.ssm.controller;

import com.ssm.commons.APIURlUtils;
import com.ssm.utils.HttpClientUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public Object test(){


        HashMap<String,String> map = new HashMap<>();
        map.put("page","2");
        map.put("limit","2");
        HttpClientUtils.get(APIURlUtils.QUERY_ALLPRODUCT_PAGE);
        ;
        return "hello world";
    }

@Test
    public void run(){
        HashMap<String,String> map = new HashMap<>();
        map.put("page","2");
        map.put("limit","2");
    String s = HttpClientUtils.get(APIURlUtils.QUERY_ALLPRODUCT_PAGE);
    System.out.println(s);



    ;

}

}
