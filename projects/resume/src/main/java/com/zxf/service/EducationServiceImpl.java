package com.zxf.service;

import com.zxf.bean.Education;
import com.zxf.dao.BaseDao;
import com.zxf.dao.EducationDaoImpl;

public class EducationServiceImpl extends BaseServiceImpl<Education> implements EducationService{
    @Override
    protected BaseDao<Education> dao() {
        return new EducationDaoImpl();
    }
}
