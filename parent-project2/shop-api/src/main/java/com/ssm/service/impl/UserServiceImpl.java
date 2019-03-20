package com.ssm.service.impl;

;
import com.ssm.commons.PageVo;
import com.ssm.dto.ResponseDto;
import com.ssm.dto.UserDto;

import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.ErrorCode;

import com.ssm.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;
@Autowired
 private ProductMapper productMapper;
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

    @Override
    public PageVo queryPageVo(Integer page, Integer limit) {

        PageVo<ProductVo> pageVo=new PageVo<>();
        pageVo.setLimit(limit);
        pageVo.setPage(page);

        //查询数据
        List<ProductVo> list= productMapper.selectProductPages((page-1)*limit,limit);
        for(ProductVo p: list){

            //遍历商品  根据id查询商品的第一个图片
        }

        //查询个数
        long count= productMapper.selectProductPagesCount();

        pageVo.setCount(count);
        pageVo.setData(list);
        Long ps=count%limit==0?(count/limit):(count/limit+1);
        pageVo.setPages(ps.intValue());//设置总页面


        return pageVo;
    }

}
