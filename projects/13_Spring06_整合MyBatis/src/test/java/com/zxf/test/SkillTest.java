package com.zxf.test;

import com.zxf.dao.SkillDao;
import com.zxf.domain.Skill;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SkillTest {
    private ApplicationContext context;
    private SkillDao dao;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("skillDao", SkillDao.class);
    }

    @Test
    public void list() {
        dao = context.getBean("skillDao", SkillDao.class);
        List<Skill> list = dao.list();
        System.out.println(list);
    }

    @Test
    public void save() {
        Skill skill = new Skill("Cpp",15);
        dao = context.getBean("skillDao", SkillDao.class);
        dao.save(skill);
    }
}
