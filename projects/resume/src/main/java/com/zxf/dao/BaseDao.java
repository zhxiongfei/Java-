package com.zxf.dao;

import java.util.List;

public interface BaseDao<T> {
    boolean remove(Integer id);
    boolean remove(List<Integer>id);
    boolean save(T bean);
    T get(Integer id);
    List<T> list();
    Integer count();
}
