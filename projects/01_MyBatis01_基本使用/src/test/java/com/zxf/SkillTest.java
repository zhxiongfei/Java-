package com.zxf;

import com.github.pagehelper.PageHelper;
import com.zxf.bean.Skill;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillTest {
    @Test
    public void select() throws Exception{
        try(Reader reader = Resources.getResourceAsReader("mybatis-config.xml")){
            // 创建工厂构建器
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            // 创建一个工厂
            SqlSessionFactory factory = builder.build(reader);

            // 创建一个session
            try (SqlSession session = factory.openSession()){
                // 执行 session 语句
                List<Skill> skills = session.selectList("skill.list");

                for (Skill skill : skills) {
                    System.out.println(skill);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void select2() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            Skill skill = session.selectOne("skill.get", 8);
            System.out.println(skill);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void select3() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            Map<String,Object> map = new HashMap<>();
            map.put("id",5);
            map.put("level",500);
            List<Skill> skills = session.selectList("skill.list2", map);
            for (Skill skill : skills) {
                System.out.println(skill);
            };
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void select4() throws Exception{
        try(SqlSession session = MyBatises.openSession()){

            Skill param = new Skill();
            param.setId(10);
            param.setLevel(800);
            param.setName("%J%");
            List<Skill> skills = session.selectList("skill.list2", param);
            for (Skill skill : skills) {
                System.out.println(skill);
            };
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void insert() throws Exception{
        try(SqlSession session = MyBatises.openSession(true)){
            Skill param = new Skill("SpringMVC", 777);
            session.insert("skill.insert3", param);

            System.out.println(param.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void update() throws Exception{
        try(SqlSession session = MyBatises.openSession(true)){
            Skill param = new Skill("iOS", 666);
            param.setId(10);
            session.insert("skill.update", param);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void delete() throws Exception{
        try(SqlSession session = MyBatises.openSession(true)){
            session.insert("skill.delete", 22);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void dynamicSQL() throws Exception{
        try(SqlSession session = MyBatises.openSession(true)){
            Map<String,Object> params = new HashMap<>();
            params.put("id",8);
            params.put("name","%s%");
            params.put("level",600);

            List<Skill> skills = session.selectList("skill.dynamicSQL",params);
            for (Skill skill : skills) {
                System.out.println(skill);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void dynamicSQL2() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            Map<String,Object> params = new HashMap<>();
            params.put("id",8);
            params.put("name","%s%");
            params.put("level",600);

            List<Skill> skills = session.selectList("skill.dynamicSQL2",params);
            for (Skill skill : skills) {
                System.out.println(skill);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void batchInsert() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            List<Skill> skills = new ArrayList<>();
            skills.add(new Skill("Java", 100));
            skills.add(new Skill("Java", 200));
            skills.add(new Skill("Java", 300));
            skills.add(new Skill("Java", 400));
            session.insert("skill.batchInsert", skills);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void batchDelete() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            List<Integer> skills = new ArrayList<>();
            skills.add(17);
            session.delete("skill.batchDelete2", skills);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void page() throws Exception{
        try(SqlSession session = MyBatises.openSession()){
            PageHelper.startPage(2,5);
            List<Skill> skills = session.selectList("skill.list");
            for (Skill skill : skills) {
                System.out.println(skill);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
