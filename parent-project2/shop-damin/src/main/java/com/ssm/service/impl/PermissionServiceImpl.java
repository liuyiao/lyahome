package com.ssm.service.impl;

import com.ssm.mapper.PermissionMapper;
import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
     private PermissionMapper permissionMapper;



    @Override
    public ResultVO showPermission(Integer no, String status) {

          //查询个数
        long l = permissionMapper.selectPermissionCount(no, status);


        //查询数据
        List<Permission> list=  permissionMapper.selectPermissionList(no,status);


        return ResultVO.success(l,list);
    }
}
