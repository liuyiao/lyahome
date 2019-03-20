package com.ssm.service;

import com.ssm.pojo.User;
import com.ssm.vo.ResultVO;

public interface UserService {


    User login(String userAccount,String password);

     int xiugai(String password1,String userAccount);

    boolean modifyUserHeadImage(Long userId, String uploadStr);

    ResultVO showUsersPageTest(Integer page, Integer limit);

    ResultVO showUsersPage(Integer page, Integer limit, String no, String mobileNumber, Integer status);

    boolean checkAccount(String userAccount);

    boolean addUser(User user);

    User queryUserById(Long userId);

    boolean modifyUser(User user);

    boolean deleteUserById(Long id);

    boolean deleteBatch(Long[] ids);

    boolean userActive(Long userId, Boolean status);

    boolean editUserRole(Long userId, Long[] rids);
}
