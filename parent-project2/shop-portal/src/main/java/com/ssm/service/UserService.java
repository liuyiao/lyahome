package com.ssm.service;


import com.ssm.commons.PageVo;
import com.ssm.dto.ResponseDto;
import com.ssm.vo.ProductVo;


public interface UserService {
    ResponseDto login(String userAccount, String password);

    PageVo<ProductVo> queryPageVo(Integer page, Integer limit);
}
