package com.zxf.service.impl;

import com.zxf.bean.User;
import com.zxf.dao.impl.UserDaoImpl;
import com.zxf.service.UserService;

import java.util.List;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public List<User> get(User user) {
        return ((UserDaoImpl)dao).get(user);
    }
}
