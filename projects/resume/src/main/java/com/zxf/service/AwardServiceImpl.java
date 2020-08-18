package com.zxf.service;

import com.zxf.bean.Award;
import com.zxf.dao.AwardDaoImpl;
import com.zxf.dao.BaseDao;

public class AwardServiceImpl extends BaseServiceImpl<Award> implements AwardService{

    @Override
    protected BaseDao<Award> dao() {
        return new AwardDaoImpl();
    }
}
