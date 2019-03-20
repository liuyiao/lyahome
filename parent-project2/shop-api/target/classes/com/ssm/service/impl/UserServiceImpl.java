package com.ssm.service.impl;

;
import com.ssm.dto.ResponseDto;
import com.ssm.dto.UserDto;

import com.ssm.mapper.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.ErrorCode;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseDto login(String userAccount, String password) {

        ResponseDto responseDto=new ResponseDto();
       User user= userMapper.queryUserAccountAndPwd(userAccount,password);
        System.out.println(user+"------------user");
       if(user==null){

           return ResponseDto.error(ErrorCode.LOGIN_ERROR,"用户名或者密码错误！");
       }

        UserDto userDto=new UserDto();
       //复制相同的属性
        BeanUtils.copyProperties(user,userDto);

        return ResponseDto.success(userDto);
    }



}
