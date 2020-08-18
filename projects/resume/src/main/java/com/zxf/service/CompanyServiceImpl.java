package com.zxf.service;

import com.zxf.bean.Company;
import com.zxf.dao.BaseDao;
import com.zxf.dao.CompanyDaoImpl;

public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    @Override
    protected BaseDao<Company> dao() {
        return new CompanyDaoImpl();
    }
}
