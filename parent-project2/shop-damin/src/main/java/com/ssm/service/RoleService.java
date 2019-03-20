package com.ssm.service;

import com.ssm.pojo.Role;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;

import java.util.List;

public interface RoleService {

    List<Role> queryAllRoles();


    ResultVO queryAllRolesList(String no, Integer status);

    boolean roleAdd(Role role);

    boolean editRole(Role role);

    Role queryByid(Long roleUkid);


    boolean deleteBatch(Long[] ids);

    List<ZtreeVo> showZtree(Long roleUkid);

    boolean edirTree(Long rid, Long[] aids);
}
