package com.zxf.service.impl;

import com.zxf.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        System.out.println("UserServiceImpl - login - " + username + "_" + password);
        return false;
    }
}
