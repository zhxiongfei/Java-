package com.zxf.servlet;

import bean.Customer;
import com.zxf.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save")
public class SaveServlet extends javax.servlet.http.HttpServlet {

    private final CustomerDao dao = new CustomerDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setAge(Integer.parseInt(request.getParameter("age")));
        customer.setHeight(Double.parseDouble(request.getParameter("height")));

        if (dao.save(customer)){
            response.sendRedirect("/crm/list");
        }else {
            request.setAttribute("error","插入数据库失败");
            request.getRequestDispatcher("page/error.jsp").forward(request, response);
        }
    }
}
