package com.zxf.service;

import com.zxf.bean.Education;
import com.zxf.dao.BaseDao;
import com.zxf.dao.BaseDaoImpl;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> dao = dao();
    protected abstract BaseDao<T> dao();

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
