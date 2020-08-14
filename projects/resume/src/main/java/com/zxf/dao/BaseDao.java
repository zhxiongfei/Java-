package com.zxf.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class BaseDao {
    protected static JdbcTemplate tpl;
    static {
        try (InputStream is = WebsiteDao.class.getClassLoader().getResourceAsStream("druid.properties")){
            // 获取连接池
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            // 创建tpl
            tpl = new JdbcTemplate(ds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
