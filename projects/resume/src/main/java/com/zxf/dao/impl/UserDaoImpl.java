package com.zxf.dao.impl;

import com.zxf.bean.User;
import com.zxf.dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public boolean save(User bean) {
        Integer id = bean.getId();
        String sql;
        List<Object> args = new ArrayList<>();
        args.add(bean.getPassword());
        args.add(bean.getEmail());
        args.add(bean.getPhoto());
        args.add(bean.getIntro());
        args.add(bean.getName());
        args.add(bean.getBirthday());
        args.add(bean.getAddress());
        args.add(bean.getPhone());
        args.add(bean.getJob());
        args.add(bean.getTrait());
        args.add(bean.getInterests());
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO user(password, email, photo, intro, name, birthday, address, phone, job, trait, interests) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE user SET password = ?, email = ?, photo = ?, intro = ?, name = ?, birthday = ?, address = ?, phone = ?, job = ?, trait = ?, interests = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    @Override
    public User get(Integer id) {
        String sql = "SELECT id, created_time, password, email, photo, intro, name, birthday, address, phone, job, trait, interests FROM user WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> list() {
        String sql = "SELECT id, created_time, password, email, photo, intro, name, birthday, address, phone, job, trait, interests FROM user";
        return tpl.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> get(User user) {
        String sql = "SELECT id, created_time, password, email, photo, intro, name, birthday, address, phone, job, trait, interests FROM user WHERE email = ? AND password = ?";
        return tpl.query(sql, new BeanPropertyRowMapper<>(User.class), user.getEmail(), user.getPassword());
    }
}
