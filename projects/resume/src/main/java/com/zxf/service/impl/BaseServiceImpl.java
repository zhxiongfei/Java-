package com.zxf.service.impl;

import com.zxf.dao.BaseDao;
import com.zxf.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseDao<T> dao = newDao();
    protected BaseDao<T> newDao(){
        String clsName = getClass().getName().replace(".service.",".dao.")
                .replace("Service","Dao");
        try {
            return (BaseDao<T>) Class.forName(clsName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean remove(Integer id) { return dao.remove(id); }

    public boolean remove(List<Integer> ids){
        return dao.remove(ids);
    }

    public boolean save(T bean){
        return dao.save(bean);
    }

    public T get(Integer id){
        return dao.get(id);
    }

    public List<T> list(){
        return dao.list();
    }

    public int count(){
        return dao.count();
    }
}
