package com.zxf.dao.impl;

import com.zxf.bean.Project;
import com.zxf.dao.ProjectDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {

    @Override
    protected String table() {
        return "award";
    }

    @Override
    public boolean save(Award bean) {
        Integer id = bean.getId();
        String sql;
        List<Object> args = new ArrayList<>();
        args.add(bean.getName());
        args.add(bean.getImage());
        args.add(bean.getIntro());
        if (id == null || id < 1){
            sql = "INSERT INTO award(name, image, intro) VALUES(?,?,?)";
        }else {
            sql = "UPDATE award SET name = ?, image = ?, intro = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    @Override
    public Project get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Project.class), id);
    }

    @Override
    public List<Project> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Project.class));
    }
}
