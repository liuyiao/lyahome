<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/19
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/bt3/css/bootstrap.min.css" />
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bt3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <img alt="140x140" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/140/140/default.jpg" />
        </div>
        <div class="col-md-4 column">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/user/login" method="post" role="form">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" name="userAccount" class="form-control" id="inputEmail3" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" id="inputPassword3" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
