package com.zxf.dao.impl;

import com.zxf.bean.Skill;
import com.zxf.dao.SkillDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends BaseDaoImpl<Skill> implements SkillDao {

    /**
     * 添加或更新
     * */
    public boolean save(Skill skill){
        Integer id = skill.getId();
        String sql;
        List<Object> args = new ArrayList<>();
        args.add(skill.getName());
        args.add(skill.getLevel());
        if (id == null || id < 1){
            sql = "INSERT INTO skill(name, level) VALUES(?, ?)";
        }else {
            sql = "UPDATE skill SET name = ?, level = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    /**
     * 根据 id 获取 Website
     * */
    public Skill get(Integer id){
        String sql = "SELECT id, created_time, name, level FROM skill WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Skill.class), id);
    }

    /**
     * 获取所有数据
     * */
    public List<Skill> list(){
        String sql = "SELECT id, created_time, name, level FROM skill";
        List<Skill> list = tpl.query(sql, new BeanPropertyRowMapper<>(Skill.class));
        return list;
    }
}
