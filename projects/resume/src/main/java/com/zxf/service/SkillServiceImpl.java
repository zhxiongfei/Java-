package com.zxf.service;

import com.zxf.bean.Skill;
import com.zxf.dao.BaseDao;
import com.zxf.dao.SkillDaoImpl;

public class SkillServiceImpl extends BaseServiceImpl<Skill> implements SkillService {

    @Override
    protected BaseDao dao() {
        return new SkillDaoImpl();
    }
}
