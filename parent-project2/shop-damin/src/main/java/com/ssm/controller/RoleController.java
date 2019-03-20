package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.ssm.pojo.Role;

import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/role/allroles")
    @ResponseBody
    public  Object queryAllRolse(){

        List<Role> roles = roleService.queryAllRoles();



        return roles;
    }

    @RequiresPermissions(value = {"role:view","role:query"},logical = Logical.AND)
    @RequestMapping("/role/list")
    @ResponseBody
    public Object roleList(@RequestParam(defaultValue = "1")Integer page,
                           @RequestParam(defaultValue = "10")Integer limit,
                           String no,
                           Integer status){

        PageHelper.startPage(page,limit);//开启分页

        ResultVO vo= roleService.queryAllRolesList(no,status);


     return vo;

    }



    @RequestMapping("role/add")
    @ResponseBody
    public Object roleAdd(Role role){
  boolean b = roleService.roleAdd(role);

        if(b){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }


    @RequestMapping("role/querybyid")
    @ResponseBody
    public Object queryByid(Long roleUkid){

        Role role= roleService.queryByid(roleUkid);


        return role;

    }





    @RequestMapping("role/editrole")
    @ResponseBody
    public Object editRole(Role role){

        boolean b=   roleService.editRole(role);


        if(b){
            return ResultVO.success();
        }

        return ResultVO.error("error");


            }


    @RequestMapping(value = "/role/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public  Object deletUserBatch(@RequestParam("ids") Long[] ids){
        //去删除操作
        boolean f=  roleService.deleteBatch(ids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

    @RequiresPermissions("role:showtree")
@RequestMapping("/role/ztree")
@ResponseBody
public Object rolePermissions(@RequestParam Long roleUkid){

             List<ZtreeVo> list=roleService.showZtree(roleUkid);

             return list;
}

    @RequiresPermissions("role:updatepermission")
    @RequestMapping("/role/editpermission")
    @ResponseBody
public Object  editpermission(@RequestParam Long rid,Long[] aids){

        boolean f=  roleService.edirTree(rid,aids);

return f?ResultVO.success():ResultVO.error();
    }



     }
