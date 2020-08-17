package com.zxf.service;

import com.zxf.bean.Website;
import com.zxf.dao.*;

public class WebsiteServiceImpl extends BaseServiceImpl<Website> implements WebsiteService{
    @Override
    protected BaseDao<Website> dao() {
        return new WebsiteDaoImpl();
    }
}
