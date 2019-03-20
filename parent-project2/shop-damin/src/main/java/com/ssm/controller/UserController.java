package com.ssm.controller;

import com.google.code.kaptcha.Constants;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

@Autowired
    private UserService userService;




    @RequestMapping("userlogin")
    public  String  userLogin(@RequestParam String userAccount,
                              @RequestParam String password,
                              @RequestParam String imgCode){
        //先去校验验证码

        //先去获取session中的验证码  Constants.KAPTCHA_SESSION_KEY
        String  code = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(!imgCode.equalsIgnoreCase(code)){
            //验证码错误
            return "login";
        }
        //shiro认证
        //先获取当前用户
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
        try {
            //记住我
            token.setRememberMe(true);
            subject.login(token);
            //登陆成功

            //存储session
            subject.getSession().setAttribute("userAccount",userAccount);

        }catch (UnknownAccountException e){
            //没有改用户
            e.printStackTrace();
            return "login";

        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            //密码不正确
            return "login";
        }catch (LockedAccountException e){
            //账号被锁定
            e.printStackTrace();
            return "login";
        }catch (AuthenticationException e) {
            //其他异常
            e.printStackTrace();
            return "login";
        }

        return "admin/index";


    }







@RequestMapping(value = "/userlogin2",method = RequestMethod.POST)
    public String userLogin(@RequestParam String userAccount,
                            @RequestParam String password,
                            @RequestParam String imgCode,
                            HttpSession session){

    //校验验证码
    //取出session中的验证码 Constants.KAPTCHA_SESSION_KEY
    String imgCode_= (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

    if (!imgCode.equals(imgCode_)){
        //验证码不正确
        return "login";
    }
    //验证码 正确  比较用户名和密码

    User user= userService.login(userAccount, password);
if (user!=null){
    return "admin/index";
}
return "login";
}


@RequestMapping("/undefined")
public String undefined(){

    return "undefined1";
}

@RequestMapping("/undefined1")
  public String undefined1(  @RequestParam String userAccount,

                           @RequestParam String password1,
                           @RequestParam String password2
                          ){
    if(password1.equals(password2)){
        int xiugai = userService.xiugai(password1,userAccount);

        if (xiugai==1){
            return "redirect:admin/index";
        }

    }
    return "undefined1";

}
   @RequiresAuthentication
    @RequestMapping(value = "/uploadheadimage",method = RequestMethod.POST)
    @ResponseBody
    public  Object  uploadHeadImage(MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException {
        Map<String,String> res=new HashMap<>();
        //上传图片吧图片上传到static/imgs/head/文件夹下
        String realPathDir = request.getServletContext().getRealPath("/static/imgs/head");
        File fileDir = new File(realPathDir);
        if(!fileDir.isDirectory()){
            fileDir.delete();
            fileDir.mkdirs();//创建
        }
        if(!file.isEmpty()){
            //文件上传
            //构建文件名
            String fileName= UUID.randomUUID().toString().replace("-","")+"_"+file.getOriginalFilename();//获取上传文件的全名

            File dest = new File(realPathDir + "/" + fileName);
            //要上传到服务器的路径
            String  uploadStr="static/imgs/head/"+fileName;

            //文件复制
            file.transferTo(dest);

            //从session中获取userId
            User user = (User) session.getAttribute("activeUser");

            //修改用户头像信息
            boolean f=  userService.modifyUserHeadImage(user.getUserId(),uploadStr);

            if(f){
                //替换session
                user.setHeadimageurl(uploadStr);
                session.setAttribute("activeUser",user);
                res.put("code","0");
                return res;

            }

        }

        res.put("code","1");
        return res;
    }





    /**
     * 测试  layui数据表格
     * @param page
     * @param limit
     * @return
             */
    @RequestMapping("table/user")
    public @ResponseBody Object userListTest(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit){

        ResultVO vo= userService.showUsersPageTest(page,limit);

        return vo;
    }




    /**
     *
     * @param page 当前页
     * @param limit 每页数据
     * @param no  用户id/账号
     * @param mobileNumber  手机号
     * @param status 用户状态
     * @return
     */

@RequiresPermissions("user:view")
    @RequestMapping("/user/list")
    public @ResponseBody Object userListTest(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit,
                                             String no,
                                             String mobileNumber,
                                             Integer status){

        ResultVO vo= userService.showUsersPage(page,limit,no,mobileNumber,status);

        return vo;
    }


    @RequestMapping("/user/checkaccount")
    @ResponseBody
public Object checkAccount(@RequestParam String userAccount){
        boolean b = userService.checkAccount(userAccount);
        if (b){
            return  ResultVO.error();//500
        }

return  ResultVO.success();
    }


    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    public String userAdd(User user, Model model){
        String password = user.getPassword();

    boolean b = userService.addUser(user);
       user.setPassword(password);
    model.addAttribute("pwd",user);

    if(b){
        return "user/userinfo";
    }

return  "user/useradd";


    }


    @RequiresPermissions("user:update")
    @RequestMapping("/user/modify")
    public  String  userModifyView(Long userId,Model model){
        //做页面跳转
        //根据id查询用户数据

        User user= userService.queryUserById(userId);
        //存储到域对象
        model.addAttribute("user",user);

        return "user/usermodify";
    }

    //shop-admin/user/domodify
    @RequiresPermissions("user:update")
    @RequestMapping("/user/domodify")
    public  String  userModifyView(User user,Model model){
        //做页面跳转
        //根据id查询用户数据

        boolean f= userService.modifyUser(user);
        //存储到域对象
        model.addAttribute("user",user);

        if(f){
            return "user/userinfo";
        }

        //修改失败
        return "user/usermodify";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Object deletUser(@PathVariable Long id){
        //去删除操作
        boolean f=  userService.deleteUserById(id);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

    /**
     *    url:'${pageContext.request.contextPath}/user/deletebatch',
     */
    @RequiresPermissions("user:deletebatch")
    @RequestMapping(value = "/user/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public  Object deletUserBatch(@RequestParam("ids") Long[] ids){
        //去删除操作
        boolean f=  userService.deleteBatch(ids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

    @RequiresPermissions("user:active")
    @RequestMapping(value="/user/active")
    @ResponseBody
public Object  userActive(@RequestParam Long userId,@RequestParam Boolean status){
        boolean f= userService.userActive(userId,status);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

}



//给用户奉陪角色
@RequiresPermissions("user:allocaterole")
    @RequestMapping("/userroleedit")
    @ResponseBody
public Object userRoleEdit(@RequestParam Long userId,
                           Long[] rids){

        boolean f=  userService.editUserRole(userId,rids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");
    }



}