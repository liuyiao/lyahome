package com.ssm.service;


import com.ssm.commons.PageVo;
import com.ssm.dto.ResponseDto;

public interface UserService {
    ResponseDto login(String userAccount, String password);

    PageVo queryPageVo(Integer page, Integer limit);
}
