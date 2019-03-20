package com.ssm.controller;

import com.ssm.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @RequestMapping("/test")

    public  void  test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        Map<String,String> map=new HashMap<>();
        map.put("001","莎士比亚");
        map.put("002","李白");

        String s = JsonUtils.objectToJson(map);
        response.getWriter().write(s);

    }

}
