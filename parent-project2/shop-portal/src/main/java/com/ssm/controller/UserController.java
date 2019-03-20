package com.ssm.controller;


import com.ssm.commons.PageVo;
import com.ssm.dto.ResponseDto;
import com.ssm.service.UserService;

import com.ssm.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/login")
    public  String userLogin(@RequestParam String userAccount,
                             @RequestParam String password, HttpSession session){

       ResponseDto responseDto= userService.login(userAccount,password);
        Integer code = responseDto.getError_code();
        if(code==200){//登录成功
            //存储域对象
            session.setAttribute("ActiveUser",responseDto.getObj());

            return "index";
        }else{//登录失败 往哪里跳

            //网登录页面跳转
            return  "login";
        }


    }


    @RequestMapping(value="/product",method = RequestMethod.GET)
    @ResponseBody
    public  Object  queruProductPages(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "6") Integer limit, Model model){

        PageVo<ProductVo> pageVo=userService.queryPageVo(page,limit);

        System.out.println(pageVo);
        model.addAttribute("page",pageVo);
        return pageVo;

    }

}
