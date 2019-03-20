package com.ssm.mapper;

import com.ssm.pojo.Role;
import com.ssm.pojo.RoleExample;
import com.ssm.vo.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleUkid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleUkid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();

    List<Role> selectRoleList(@Param("no")String no,@Param("status") Integer status);

    long selectCountRole(@Param("no")String no,@Param("status") Integer status);

    int roleAdd(@Param("role") Role role);

    int editRole(@Param("role")Role role);

    Role queryByid(@Param("id") Long roleUkid);

    int deleteBatch(@Param("ids") Long[] ids);

    List<ZtreeVo> showZtree(Long roleUkid);

    void deletePermission(Long rid);

    void addBatch(@Param("rid")Long roleUkid,@Param("ids") Long[] aids);
}