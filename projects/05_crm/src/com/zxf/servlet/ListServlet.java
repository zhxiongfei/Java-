package com.zxf.servlet;

import bean.Customer;
import com.zxf.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class ListServlet extends javax.servlet.http.HttpServlet {

    private final CustomerDao dao = new CustomerDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers",dao.list());
        request.getRequestDispatcher("page/list.jsp").forward(request, response);
    }
}
