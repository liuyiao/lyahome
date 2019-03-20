<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/7
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="../includes/include.jsp"%>
<body>

<table id="demo" lay-filter="test"></table>

<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: '${pageContext.request.contextPath}/table/user/' //数据接口
            ,page: true //开启分页
            ,limit:10//每页数据大小
            ,cols: [[ //表头
                {field: 'userId', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'userAccount', title: '用户名', width:80}
                ,{field: 'userName', title: '真实姓名', width:80, sort: true}
                ,{field: 'mobileNumber', title: '手机号', width:80}
                ,{field: 'email', title: '邮箱', width: 177}
                ,{field: 'sex', title: '性别', width: 80, sort: true}
            ]]
        });

    });
</script>
</body>
</html>
