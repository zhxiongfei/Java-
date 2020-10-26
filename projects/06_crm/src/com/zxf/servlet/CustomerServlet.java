package com.zxf.servlet;

import com.zxf.bean.Customer;
import com.zxf.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

    private final CustomerDao dao = new CustomerDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = request.getRequestURI();
        String[] cmps = url.split("/");
        if (cmps.length == 1) return;
        String methodName = cmps[cmps.length - 1];
        System.out.println(methodName);
        try { // 反射
            Method method = getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("customers",dao.list());
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setAge(Integer.parseInt(request.getParameter("age")));
        customer.setHeight(Double.parseDouble(request.getParameter("height")));

        if (dao.save(customer)){
            response.sendRedirect("/crm/customer/list");
        }else {
            request.setAttribute("error","插入数据库失败");
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
        }
    }
}
