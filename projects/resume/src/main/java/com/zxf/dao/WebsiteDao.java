package com.zxf.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zxf.bean.Website;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.Customizer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WebsiteDao extends BaseDao{

    /**
    * 删除
    * */
    public boolean remove(Integer id){
        return false;
    }

    /**
     * 添加或更新
     * */
    public boolean save(Website website){
        Integer id = website.getId();
        String sql;
        List<Object> args = new ArrayList<>();
        args.add(website.getFooter());
        if (id == null || id < 1){
            sql = "INSERT INTO website(footer) VALUES(?)";
        }else {
            sql = "UPDATE website SET footer = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    /**
     * 根据 id 获取 Website
     * */
    public Website get(Integer id){
        return null;
    }

    /**
     * 获取所有数据
     * */
    public List<Website> list(){
        String sql = "SELECT id,created_time, footer FROM website";
        List<Website> list = tpl.query(sql, new BeanPropertyRowMapper<>(Website.class));
        return list;
    }

    /**
     * 获取统计值e
     * */
    public int count(){
        return 1;
    }
}
