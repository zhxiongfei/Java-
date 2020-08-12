package com.zxf.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zxf.bean.Website;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WebsiteDao {
    private static JdbcTemplate tpl;
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
        String sql = "";
        List<Object> args = new ArrayList<>();
        args.add(website.getFooter());
        if (id == null){
            sql = "INSERT INFO website(footer) VALUES(?)";
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
        return null;
    }

    /**
     * 获取统计值e
     * */
    public int count(){
        return 1;
    }
}
