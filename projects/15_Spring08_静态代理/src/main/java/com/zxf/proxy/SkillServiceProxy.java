package com.zxf.proxy;

import com.zxf.service.SkillService;

public class SkillServiceProxy extends SkillService {
    private SkillService target;

    public void setTarget(SkillService target) {
        this.target = target;
    }

    @Override
    public boolean save(Object skill) {
        System.out.println("SkillServiceProxy - 1");
        boolean res = super.save(skill);
        System.out.println("SkillServiceProxy - 2");
        return res;
    }
}
