package com.zxf;

import com.zxf.bean.Skill;
import com.zxf.dao.SkillDao;
import com.zxf.dao.impl.SkillDaoImpl;
import org.junit.Test;

import java.util.List;

public class SkillTest {

    @Test
    public void get() {
        SkillDao dao = new SkillDaoImpl();
        Skill skill = dao.get(6);
        System.out.println(skill);
    }

    @Test
    public void list() {
        SkillDao dao = new SkillDaoImpl();
        List<Skill>list = dao.list();
        System.out.println(list);
    }

    @Test
    public void save() {
        SkillDao dao = new SkillDaoImpl();
        boolean res = dao.save(new Skill("Jack", 2000));
        System.out.println(res);
    }

    @Test
    public void update() {
        SkillDao dao = new SkillDaoImpl();
        Skill skill = new Skill("iOS", 2000);
        skill.setId(20);
        boolean res = dao.update(skill);
        System.out.println(res);
    }

    @Test
    public void delete() {
        SkillDao dao = new SkillDaoImpl();
        Skill skill = new Skill();
        skill.setId(26);
        boolean res = dao.delete(skill);
        System.out.println(res);
    }
}
