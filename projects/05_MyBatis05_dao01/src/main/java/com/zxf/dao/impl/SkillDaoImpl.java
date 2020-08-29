package com.zxf.dao.impl;

import com.zxf.bean.Skill;
import com.zxf.dao.SkillDao;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SkillDaoImpl implements SkillDao {

    @Override
    public boolean save(Skill skill) {
        try (SqlSession session = MyBatises.openSession(true)){
            return session.insert("skill.save", skill) > 0;
        }
    }

    @Override
    public boolean update(Skill skill) {
        try (SqlSession session = MyBatises.openSession(true)){
            return session.update("skill.update", skill) > 0;
        }
    }

    @Override
    public boolean delete(Skill skill) {
        try (SqlSession session = MyBatises.openSession(true)){
            return session.delete("skill.remove", skill) > 0;
        }
    }

    @Override
    public Skill get(Integer id) {
        try (SqlSession session = MyBatises.openSession()){
            return session.selectOne("skill.get", id);
        }
    }

    @Override
    public List<Skill> list() {
        try (SqlSession session = MyBatises.openSession()){
            return session.selectList("skill.list");
        }
    }
}
