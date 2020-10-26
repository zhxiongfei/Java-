package com.zxf.dao;

import com.zxf.bean.Customer;
import com.zxf.util.Dbs;

import java.awt.image.DataBufferShort;
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

        String sql = "INSERT INTO customer(name, age, height) VALUES (?,?,?)";
        return Dbs.update(sql,customer.getName(), customer.getAge(), customer.getHeight()) > 0;
    }

    /**
     * 返回所有 customer 数据
     * @return
     */
    public List<Customer> list(){

        String sql = "SELECT id,name,age,height FROM customer";
        // lambda 表达式
        return Dbs.query(sql,((res, row) -> {
            Customer customer = new Customer();
            customer.setId(res.getInt("id"));
            customer.setName(res.getString("name"));
            customer.setAge(res.getInt("age"));
            customer.setHeight(res.getDouble("height"));
            return customer;
        }));
    }
}
