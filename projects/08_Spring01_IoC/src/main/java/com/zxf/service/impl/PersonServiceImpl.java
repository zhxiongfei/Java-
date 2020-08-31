package com.zxf.service.impl;

import com.zxf.dao.PersonDao;
import com.zxf.factory.GeneralFactory;
import com.zxf.service.PersonService;

public class PersonServiceImpl implements PersonService {
//    PersonDao dao = GeneralFactory.get("personDao");
    PersonDao dao;

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
