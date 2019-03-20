package com.ssm.controller;


import com.github.pagehelper.PageHelper;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;



    @RequestMapping("/permissions")
    @ResponseBody
    public Object permissionList(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "5") Integer limit,
                                  Integer no,
                                  String status){

        PageHelper.startPage(page,limit);

        ResultVO resultVO = permissionService.showPermission(no, status);



        return resultVO;


    }




}
