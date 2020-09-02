package com.zxf.dao;

import com.zxf.domain.Skill;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface SkillDao {

    List<Skill> list();

    @Insert("INSERT INTO skill(name, level) VALUES (#{name},#{level})")
    boolean save(Skill skill);

}