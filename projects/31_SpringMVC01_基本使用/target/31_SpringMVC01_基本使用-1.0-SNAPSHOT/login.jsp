<%--
  Created by IntelliJ IDEA.
  User: liuxiaoyong
  Date: 2020/9/10
  Time: 11:16 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

    <form action="encoding/login" method="post">
        <div>
            <input type="text" name="username" placeholder="请输入用户名" />
        </div>
        <div>
            <input type="text" name="password" placeholder="请输入密码" />
        </div>
        <div>
            <button type="submit">登录</button>
        </div>
    </form>

</body>
</html>
