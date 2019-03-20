package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/v/{path}/{view}")
    public  String  view(@PathVariable String path,
                         @PathVariable String view){

        return path+"/"+view;
    }
    @RequestMapping("/index")
    public  String  index(){
        return "admin/index";

    }
    @RequestMapping("/login")
    public  String  login(){
        return "login";

    }
    @RequestMapping("/")
    public  String  login2(){
        return "login";

    }
}
