<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/11
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%@include file="includes/include.jsp"%>

<%--<jsp:include page="./includes/include.jsp"/>--%>
<form class="layui-form" action="${pageContext.request.contextPath}/undefined1" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户账号</label>
        <div class="layui-input-block">
            <input type="text" name="userAccount"   lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" name="password"   lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-block">
            <input type="password" name="password1" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">再次输入密码</label>
        <div class="layui-input-block">
            <input type="password" name="password2" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交 formDemo   lay-filter的值  用来过滤表单的
        form.on('submit(formDemo)', function(data){

            //layer.msg  弹框
            //layer.msg(JSON.stringify(data.field));

           // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            //return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            return true;
        });

    });


</script>

</body>
</html>
