package com.ssm.service;


import com.ssm.dto.ResponseDto;

public interface UserService {
    ResponseDto login(String userAccount, String password);


}
