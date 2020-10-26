package com.zxf.proxy;

import com.zxf.service.UserService;
import com.zxf.service.impl.UserServiceImpl;

public class UserServiceProxy implements UserService {

    private UserServiceImpl target;

    public void setTarget(UserServiceImpl target) {
        this.target = target;
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("UserServiceProxy - 1");
        boolean res = target.login(username, password);
        System.out.println("UserServiceProxy - 2");
        return res;
    }
}
