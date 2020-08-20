package com.zxf.service;

import com.zxf.bean.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<User> get(User user);
}
