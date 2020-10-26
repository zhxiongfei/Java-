package com.zxf.dao.impl;

import com.zxf.bean.Website;
import com.zxf.dao.WebsiteDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class WebsiteDaoImpl extends BaseDaoImpl<Website> implements WebsiteDao {

    /**
    * 删除
    * */
    @Override
    public boolean remove(List<Integer> id) {
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
        String sql = "SELECT id,created_time, footer FROM website WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Website.class), id);
    }

    /**
     * 获取所有数据
     * */
    public List<Website> list(){
        String sql = "SELECT id,created_time, footer FROM website";
        List<Website> list = tpl.query(sql, new BeanPropertyRowMapper<>(Website.class));
        return list;
    }

}
