package com.zxf.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zxf.bean.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

public class CustomerDao {

    public static JdbcTemplate tpl;
    static {
        try {
            // 获取连接池
            Properties properties = new Properties();
            properties.load(CustomerDao.class.getClassLoader().getResourceAsStream("druid.properties"));
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            // 创建tpl
            tpl = new JdbcTemplate(ds);
        }catch (Exception e){

        }
    }

    public List<Customer> list(){

        String sql = "SELECT id, name, height FROM customer";
        List<Customer> list = tpl.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        return list;
    }

}
