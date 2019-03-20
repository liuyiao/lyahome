package com.ssm.mapper;

import com.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    int xiugai(String password1,String userAccount);

    int modifyHeadImage(@Param("uid") Long userId,@Param("headUrl") String uploadStr);
    List<UserRoleExpand> selectUserAndRoleByPages(@Param("page")Integer page,
                                                  @Param("limit")Integer limit,
                                                  @Param("no")String no,
                                                  @Param("mobileNumber") String mobileNumber,
                                                  @Param("status") Integer status);

    long selectUserAndRoleCount( @Param("no")String no,
                                 @Param("mobileNumber") String mobileNumber,
                                 @Param("status") Integer status);

    List<User> checkAccount(@Param("userAccount") String userAccount);

    int addUser(@Param("user") User user);

   int deleteBatch(@Param("ids") Long[] ids);

    int userActive(@Param("id") Long userId, @Param("status") int status);

    List<Role> selectAllRolesByAccount(String userAccount);

    List<Permission> selectAllPermissionsByAccount(String userAccount);
}