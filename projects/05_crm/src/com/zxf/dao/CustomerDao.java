package com.zxf.dao;

import bean.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final String URL = "jdbc:mysql://localhost:3306/crm";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * 将customer保存的熬数据库
     * @param customer 要保存的对象
     * @return 是否成功
     */
    public boolean save(Customer customer){

        try {
            String sql = "INSERT INTO customer(name, age, height) VALUES (?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, customer.getName());
                pstmt.setInt(2, customer.getAge());
                pstmt.setDouble(3, customer.getHeight());
                return pstmt.executeUpdate() > 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回所有 customer 数据
     * @return
     */
    public List<Customer> list(){
        try {
            String sql = "SELECT id,name,age,height FROM customer";
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet res = pstmt.executeQuery()) {

                List<Customer> customers = new ArrayList<>();
                while (res.next()) {
                    Customer customer = new Customer();

                    customer.setId(res.getInt("id"));
                    customer.setName(res.getString("name"));
                    customer.setAge(res.getInt("age"));
                    customer.setHeight(res.getDouble("height"));

                    customers.add(customer);
                }
                return customers;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
