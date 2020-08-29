package com.zxf;

import com.zxf.bean.Skill;
import com.zxf.dao.SkillDao;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SkillTest {

    @Test
    public void get() {
        try (SqlSession session = MyBatises.openSession(false)) {
            // 生成 SkillDao 的代理对象
            SkillDao dao = session.getMapper(SkillDao.class);
            Skill skill = dao.get(10);
            System.out.println(skill);
            Assert.assertTrue(skill != null);
        }
    }

    @Test
    public void list() {
        try (SqlSession session = MyBatises.openSession(false)) {
            // 生成 SkillDao 的代理对象
            SkillDao dao = session.getMapper(SkillDao.class);
            List<Skill> skills = dao.list();
            System.out.println(skills);
            Assert.assertTrue(skills != null);
        }
    }

    @Test
    public void save() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成 SkillDao 的代理对象
            SkillDao dao = session.getMapper(SkillDao.class);
            boolean res = dao.save(new Skill("Objective-C", 3000));
            System.out.println(res);
            Assert.assertTrue(res);
        }
    }

    @Test
    public void update() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成 SkillDao 的代理对象
            SkillDao dao = session.getMapper(SkillDao.class);
            Skill skill = new Skill("C++",2000);
            skill.setId(24);
            boolean res = dao.update(skill);
            System.out.println(res);
            Assert.assertTrue(res);
        }
    }

    @Test
    public void remove() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成 SkillDao 的代理对象
            SkillDao dao = session.getMapper(SkillDao.class);
            Skill skill = new Skill("C++",2000);
            skill.setId(28);
            boolean res = dao.remove(skill);
            System.out.println(res);
            Assert.assertTrue(res);
        }
    }
}
