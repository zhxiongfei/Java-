package com.zxf.servlet;


import com.zxf.bean.Customer;

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
public class ListServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/crm";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String sql = "SELECT id,name,age,height FROM curtomer";
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet res = pstmt.executeQuery()) {

                List<Customer> customers = new ArrayList<>();
                if (res.next()) {
                    Customer customer = new Customer();

                    customer.setId(res.getInt("id"));
                    customer.setName(res.getString("name"));
                    customer.setAge(res.getInt("age"));
                    customer.setHeight(res.getDouble("height"));

                    customers.add(customer);
                }

                request.setAttribute("customers",customers);
                request.getRequestDispatcher("page/list.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}