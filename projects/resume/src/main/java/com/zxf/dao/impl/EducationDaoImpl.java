package com.zxf.dao.impl;

import com.zxf.bean.Education;
import com.zxf.dao.EducationDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl extends BaseDaoImpl<Education> implements EducationDao {

    /**
     * 添加或更新
     * */
    public boolean save(Education education){
        Integer id = education.getId();
        String sql;
        List<Object> args = new ArrayList<>();
        args.add(education.getName());
        args.add(education.getType());
        args.add(education.getIntro());
        args.add(education.getBeginDay());
        args.add(education.getEndDay());
        if (id == null || id < 1){
            sql = "INSERT INTO education(name, type, intro, begin_day, end_day) VALUES(?,?,?,?,?)";
        }else {
            sql = "UPDATE education SET name = ?, type = ?, intro = ?, begin_day = ?, end_day =? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    /**
     * 根据 id 获取 Website
     * */
    public Education get(Integer id){
        String sql = "SELECT id, name, type, intro, begin_day, end_day FROM education WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Education.class), id);
    }

    /**
     * 获取所有数据
     * */
    public List<Education> list(){
        String sql = "SELECT id, name, type, intro, begin_day, end_day FROM education";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Education.class));
    }
}
