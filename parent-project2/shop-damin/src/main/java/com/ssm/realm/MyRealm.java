package com.ssm.realm;

import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.pojo.UserExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 自定义Realm累
 *
 * 一般继承 AuthorizingRealm（授权）即可；其继承了AuthenticatingRealm（即身份验证），
 * 而且也间接继承了CachingRealm（带有缓存实现）。
 * 如果只是想认证可以单独的去继承AuthenticatingRealm。
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    /**
     * 认证的方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //吧authenticationToken强转成 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        //去数据库根据用户查询
        UserExample example=new UserExample();
        example.createCriteria().andUserAccountEqualTo(username);
      List<User> user=  userMapper.selectByExample(example);
      if(user==null||user.size()==0){
          //没有该账号
          throw  new UnknownAccountException("没有该用户");
      }
      //需要让shiro去帮我们匹配密码了

        //真正开发的时候 需要把盐值存储到数据库表中  可以使随机生成的字符串。
         String salt = "1231~!@##%$RT!@#@/.%#$@";
        /**
         * Object principal 用户名
         * Object hashedCredentials,  数据库的密码
         * ByteSource credentialsSalt,  盐值
         * String realmName  ream的名字
         */
        SimpleAuthenticationInfo simpleAuthenticationInfo=   new SimpleAuthenticationInfo(username, user.get(0).getPassword(), ByteSource.Util.bytes(salt),this.getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 授权的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        //获取用户名
        String userAccount = (String) principalCollection.getPrimaryPrincipal();
        //查询用户拥有所有角色
        List<Role> roles = userMapper.selectAllRolesByAccount(userAccount);

        //查询用户拥有的所有权限信息

        List<Permission> permissions = userMapper.selectAllPermissionsByAccount(userAccount);

        //创建两个集合  来存储权限的代码  和角色的名字
        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        //赋值角色
        for (Role r : roles) {
            roleList.add(r.getRoleName());

        }
        //赋值 权限
        for (Permission p : permissions) {
            permissionList.add(p.getPercode());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加该用户的所有权限  List<String>  权限的标志
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        //添加用户的所有角色信息  List<String> 角色名
        simpleAuthorizationInfo.addRoles(roleList);


        return simpleAuthorizationInfo;


    }
}
