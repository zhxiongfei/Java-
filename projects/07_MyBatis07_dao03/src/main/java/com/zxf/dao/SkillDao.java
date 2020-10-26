package com.zxf.dao;

import com.zxf.bean.Skill;
import org.apache.ibatis.annotations.*;
import java.util.List;

@CacheNamespace(flushInterval = 600000, size = 1024, readWrite = true)
public interface SkillDao {

    @Insert("INSERT INTO skill(name, level) VALUES(#{name},#{level})")
    boolean save(Skill skill);

    @Update("UPDATE skill SET name = #{name}, level = #{level} WHERE id = #{id}")
    boolean update(Skill skill);

    @Delete("DELETE FROM skill WHERE id = #{id}")
    boolean remove(Skill skill);

    @Select("SELECT * FROM skill WHERE id = #{id}")
    Skill get(Integer id);

    @Select("SELECT * FROM skill")
    List<Skill> list();
}
