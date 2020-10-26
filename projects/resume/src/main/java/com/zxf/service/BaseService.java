package com.zxf.service;

import java.util.List;

public interface BaseService <T>{
    boolean remove(List<Integer> ids);
    boolean save(T education);
    T get(Integer id);
    List<T> list();
    int count();
}
