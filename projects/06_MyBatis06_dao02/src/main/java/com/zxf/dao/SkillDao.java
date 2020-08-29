package com.zxf.dao;

import com.zxf.bean.Skill;

import java.util.List;

public interface SkillDao {
    boolean save(Skill skill);
    boolean update(Skill skill);
    boolean remove(Skill skill);
    Skill get(Integer id);
    List<Skill> list();
}
