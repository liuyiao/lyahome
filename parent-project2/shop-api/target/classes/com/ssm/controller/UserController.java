package com.ssm.controller;

import com.ssm.dto.ResponseDto;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param userAccount
     * @param password
     * @return
     */
    @RequestMapping(value="/api/user/login",method = RequestMethod.POST)
    @ResponseBody
    public  Object login(@RequestParam String userAccount, @RequestParam String password ){
        System.out.println(userAccount+"--"+password);
        ResponseDto vo= userService.login(userAccount,password);


        return vo;
    }

}
