package com.zxf.test;

import com.zxf.dao.SkillDao;
import com.zxf.domain.Skill;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SkillTest {

    @Test
    public void list() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SkillDao dao = ctx.getBean("skillDao", SkillDao.class);

        List<Skill> list = dao.list();
        System.out.println(list);
    }
}
