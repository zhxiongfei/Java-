<%--
  Created by IntelliJ IDEA.
  User: liuxiaoyong
  Date: 2020/7/29
  Time: 7:12 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP</title>
</head>
<body>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if ("123".equals(username) && "123".equals(password)){
%>
    <h1 style="color: blue">登录成功</h1>
<%
    } else {
%>
    <h1 style="color: red">登录失败</h1>
<%
    }
%>

</body>
</html>
