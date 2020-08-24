package com.zxf.dao.impl;

import com.zxf.bean.Contact;
import com.zxf.dao.ContactDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl extends BaseDaoImpl<Contact> implements ContactDao {


    @Override
    public boolean save(Contact bean) {
        Integer id = bean.getId();
        List<Object>args = new ArrayList<>();
        args.add(bean.getName());
        args.add(bean.getEmail());
        args.add(bean.getComment());
        args.add(bean.getSubject());
        args.add(bean.getAlreadyRead());

        String sql;
        if (id == null || id < 1){
            sql = "INSERT INTO contact(name, email, comment, subject, already_read) VALUES(?,?,?,?,?)";
        }else {
            sql = "UPDATE contact SET name = ?, email = ?, comment = ?, subject = ?, already_read = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql,args.toArray()) > 0;
    }

    @Override
    public Contact get(Integer id) {
        String sql = "SELECT id, created_time, name, email, comment, subject, already_read FROM contact WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), id);
    }

    @Override
    public List<Contact> list() {
        String sql = "SELECT id, created_time, name, email, comment, subject, already_read FROM contact";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Contact.class));
    }
}
