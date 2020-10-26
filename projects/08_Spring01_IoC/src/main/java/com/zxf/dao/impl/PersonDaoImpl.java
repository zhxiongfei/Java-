package com.zxf.dao.impl;

import com.zxf.dao.PersonDao;

public class PersonDaoImpl implements PersonDao {
    @Override
    public boolean remove(Integer id) {
        System.out.println("PersonDaoImpl remove" + id);
        return false;
    }
}
