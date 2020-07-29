package com.zxf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        outHTML(req, resp);
    }

    private void outHTML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 0.设置请求数据的编码
        request.setCharacterEncoding("UTF-8");

        // 1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. 先设置响应的内容类型 (MIMEType + 数据编码)
        response.setContentType("text/html;charset=UTF-8");

        // 3. 拿到输出流
        PrintWriter out = response.getWriter();

        // 4. 判断
        if ("123".equals(username) && "123".equals(password)){
            out.write("<h1 style=\"color: blue; border: 1px solid black;\">登录成功</h1>");
            out.write("<ul>");
            out.write("<li>个人信息</li>");
            out.write("<li>修改密码</li>");
            out.write("<li>退出登录</li>");
            out.write("</ul>");
        }else {
            out.write("<h1 style=\"color: red; border: 1px solid black;\">登录失败</h1>");
            out.write("<ul>");
            out.write("<a href=\"http:localhost:8080/crm/login.html\"> 重新登录 </a>");
            out.write("</ul>");
        }
    }
}

