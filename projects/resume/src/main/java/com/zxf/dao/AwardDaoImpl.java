package com.zxf.dao;

import com.zxf.bean.Award;
import com.zxf.bean.Company;
import com.zxf.util.Dbs;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class AwardDaoImpl extends BaseDaoImpl<Award> implements AwardDao{

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
        return Dbs.getTpl().update(sql,args.toArray()) > 0;
    }

    @Override
    public Award get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return Dbs.getTpl().queryForObject(sql, new BeanPropertyRowMapper<>(Award.class), id);
    }

    @Override
    public List<Award> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return Dbs.getTpl().query(sql, new BeanPropertyRowMapper<>(Award.class));
    }
}
