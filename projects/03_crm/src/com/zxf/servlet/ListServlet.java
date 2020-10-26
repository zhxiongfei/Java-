package com.zxf.servlet;

import com.zxf.Data;
import com.zxf.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取客户数据
        List<Customer> customers = Data.getCustomers();
        req.setAttribute("customers", customers);

        // 转发到 list.jsp 页面进行展示
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

}
