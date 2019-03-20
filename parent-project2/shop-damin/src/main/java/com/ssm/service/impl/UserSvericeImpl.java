package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.RoleMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.UserRoleMapper;
import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.pojo.UserExample;
import com.ssm.pojo.UserRoleExpand;
import com.ssm.service.UserService;
import com.ssm.utils.MD5Utils;
import com.ssm.vo.ResultVO;
import com.ssm.vo.UserRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserSvericeImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
private UserRoleMapper userRoleMapper;

    @Override
    public User login(String userAccount, String password) {

        //密码加密
       password = MD5Utils.encrypt(password);

      //调用mapper
        UserExample example = new UserExample();
        example.createCriteria().andUserAccountEqualTo(userAccount).andPasswordEqualTo(password).andStatusEqualTo(1); //1 可用  0 禁用
        List<User> users = userMapper.selectByExample(example);

        if(users!=null&&users.size()>0){
            return users.get(0);
        }

        return null;
    }


    public int xiugai(String password1,String userAccount){

        String password = MD5Utils.encrypt(password1);
        int xiugai = userMapper.xiugai(password1,userAccount);

        return xiugai;
    }


    /**
     * 修改用户头像
     * @param userId
     * @param uploadStr
     * @return
     */
    @Override
    public boolean modifyUserHeadImage(Long userId, String uploadStr) {
        if(userId!=null){
            return userMapper.modifyHeadImage(userId,uploadStr)>0;
        }
        return false;
    }

    @Override
    public ResultVO showUsersPageTest(Integer page, Integer limit) {

        //开启分页
        PageHelper.startPage(page,limit);

        //查询所有用户数据
        List<User> list = userMapper.selectByExample(null);

        //查询个数
        long row = userMapper.countByExample(null);


        return ResultVO.success(row,list);
    }

    @Override
    public ResultVO showUsersPage(Integer page, Integer limit, String no, String mobileNumber, Integer status) {
//1.分页数据 data
        ArrayList<UserRoleVO> data = new ArrayList<>();

        List<UserRoleExpand> list= userMapper.selectUserAndRoleByPages((page-1)*limit,limit,no,mobileNumber,status);

        for(UserRoleExpand ur:list){
            UserRoleVO vo=new UserRoleVO();
            vo.setRoleList(ur.getRoleList());
            //赋值两个对象相同的属性
            BeanUtils.copyProperties(ur,vo);

            //  private List<String> rids=new ArrayList<>();
            //    private List<String> rnames=new ArrayList<>();

            //该用户拥有的所有角色信息
            List<Role> roleList = ur.getRoleList();
            for(Role r:roleList){
                //把当前角色id添加到vo的角色id集合里面
                vo.getRids().add(r.getRoleUkid());
                vo.getRnames().add(r.getRoleName());
            }


            data.add(vo);
        }

        //2.查询分页 总个数 count
        long count= userMapper.selectUserAndRoleCount(no,mobileNumber,status);

        return ResultVO.success(count,data);
    }

    @Override
    public boolean checkAccount(String userAccount) {
        List<User> list = userMapper.checkAccount(userAccount);

if (list!=null&&list.size()>0){

    return true;

}
        return false;
    }

    @Override
    public boolean addUser(User user) {
       user.setPassword(MD5Utils.encrypt(user.getPassword()));

       user.setCreateTime(new Date());

        int i = userMapper.addUser(user);

        return i>0;
    }


    @Override
    public User queryUserById(Long userId) {
        if(userId!=null){
            return userMapper.selectByPrimaryKey(userId);
        }
        return null;
    }

    @Override
    public boolean modifyUser(User user) {

        user.setModifyTime(new Date());
        //user.setModifyUserId();
        int count=userMapper.updateByPrimaryKeySelective(user);


        return count>0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        if(id==null){
            return false;
        }

        return  userMapper.deleteByPrimaryKey(id)>0;
    }


    @Override
    public boolean deleteBatch(Long[] ids) {
        if(ids!=null&&ids.length>0){
            return  userMapper.deleteBatch(ids)>0;

        }

        return false;
    }

    @Override
    public boolean userActive(Long userId, Boolean status) {

        if (userId!=null&&status!=null){
            return   userMapper.userActive(userId,status?1:0)>0;
        }

  return false;



    }

    @Override
    public boolean editUserRole(Long userId, Long[] rids) {
if(userId!=null){

  userRoleMapper.deleUserRolesByUid(userId);

  if (rids!=null&&rids.length>0) {
      //新增
      userRoleMapper.insertBatch(userId, rids);

  }

  return true;
}



        return false;
    }


}
