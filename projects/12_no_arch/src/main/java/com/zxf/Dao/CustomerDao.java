package com.zxf.Dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zxf.bean.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerDao {

    /**
     * 连接池 : druid
     *
     * */
    private static JdbcTemplate tpl;
    static {
        try{
            // 获取连接池
            Properties properties = new Properties();
            properties.load(CustomerDao.class.getClassLoader().getResourceAsStream("druid.properties"));
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);

            tpl = new JdbcTemplate(ds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Customer>list(){

        String sql = "SELECT id,name,age,height FROM customer";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }
}

