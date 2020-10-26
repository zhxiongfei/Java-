package com.zxf.dao;

import com.zxf.bean.Contact;
import com.zxf.bean.ContactListParam;
import com.zxf.bean.ContactListResult;

public interface ContactDao extends BaseDao<Contact> {
    ContactListResult list(ContactListParam param);
    boolean read(Integer id);
}
