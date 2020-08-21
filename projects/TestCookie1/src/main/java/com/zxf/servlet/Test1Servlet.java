package com.zxf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/test1")
public class Test1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("name","Jack");


        //        Cookie[] cookies = request.getCookies();
//        if (cookies == null) return;
//
//        for (Cookie cookie : cookies) {
//            System.out.println(cookie.getName() + "_" + cookie.getValue());
//        }
    }
}