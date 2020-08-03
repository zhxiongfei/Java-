package com.zxf.dao;

import com.zxf.bean.Customer;
import com.zxf.util.Dbs;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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

        List<Object> args = buildArgs(customer);
        String sql;
        Integer id = customer.getId();
        if (id == null || id < 1){ // 添加
            sql = "INSERT INTO customer(name, age, height) VALUES (?,?,?)";
        }else {
            sql = "UPDATE customer SET name = ?, age = ?, height = ? WHERE id = ?";
            args.add(id);
        }

        return Dbs.getTpl().update(sql, args.toArray()) > 0;
    }

    /**
     * 返回所有 customer 数据
     * @return
     */
    public List<Customer> list(){
        String sql = "SELECT id, name, age, height FROM customer";
        return Dbs.getTpl().query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public boolean remove(Integer id){
        String sql = "DELETE FROM customer WHERE id = ?";
        return Dbs.getTpl().update(sql, id) > 0;
    }

    public Customer find(Integer id) {
        String sql = "SELECT id, name, age, height FROM customer WHERE id = ?";
        return Dbs.getTpl().queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
    }

    public boolean update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, age = ?, height = ? WHERE id = ?";
        List<Object> args = buildArgs(customer);
        args.add(customer.getId());
        return Dbs.getTpl().update(sql, args.toArray()) > 0;
    }

    private List<Object> buildArgs(Customer customer) {
        List<Object> args = new ArrayList<>();
        args.add(customer.getName());
        args.add(customer.getAge());
        args.add(customer.getHeight());
        return args;
    }
}
