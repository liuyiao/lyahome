package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/v/{path}/{jsp}")
    public  String  view(@PathVariable String path,@PathVariable String jsp){


        return path+"/"+jsp;
    }

    @RequestMapping("/login")
    public  String  view(){


        return "login";
    }
}
