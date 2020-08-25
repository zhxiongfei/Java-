package com.zxf.service.impl;

import com.zxf.bean.Contact;
import com.zxf.bean.ContactListParam;
import com.zxf.bean.ContactListResult;
import com.zxf.dao.ContactDao;
import com.zxf.service.ContactService;

public class ContactServiceImpl extends BaseServiceImpl<Contact> implements ContactService {

    @Override
    public ContactListResult list(ContactListParam param) {
        return ((ContactDao) dao).list(param);
    }

    @Override
    public boolean read(Integer id) {
        return ((ContactDao) dao).read(id);
    }
}
