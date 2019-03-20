package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.RoleMapper;
import com.ssm.pojo.Role;
import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> queryAllRoles() {
        return roleMapper.selectAll();
    }

    @Override
    public ResultVO queryAllRolesList(String no, Integer status) {


        //查询数据
        List<Role> list=  roleMapper.selectRoleList(no,status);

        //查询个数

        long count=roleMapper.selectCountRole(no,status);




        return ResultVO.success(count,list);
    }

    @Override
    public boolean roleAdd(Role role) {
        role.setCreateTime(new Date());

        int i = roleMapper.roleAdd(role);

        return i>0;
    }

    @Override
    public boolean editRole(Role role) {




        return roleMapper.editRole(role)>0;
    }

    @Override
    public Role queryByid(Long roleUkid) {



        return roleMapper.queryByid(roleUkid);
    }


    @Override
    public boolean deleteBatch(Long[] ids) {
        if(ids!=null&&ids.length>0){
            return  roleMapper.deleteBatch(ids)>0;

        }

        return false;
    }

    @Override
    public List<ZtreeVo> showZtree(Long roleUkid) {

        if (roleUkid!=null){

            List<ZtreeVo> list=roleMapper.showZtree(roleUkid);

            return list;
        }

        return null;
    }

    @Override
    public boolean edirTree(Long rid, Long[] aids) {
       if(rid!=null){

           //删除
           roleMapper.deletePermission(rid);

           if (aids!=null&&aids.length>0){
               //批量新增
               roleMapper.addBatch(rid,aids);

           }
           return true;

       }


        return false;
    }


}
