package com.zxf.service;

import com.zxf.bean.Contact;
import com.zxf.bean.ContactListParam;
import com.zxf.bean.ContactListResult;

public interface ContactService extends BaseService<Contact>{
    ContactListResult list(ContactListParam param);
    boolean read(Integer id);
}
