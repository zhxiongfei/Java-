package com.zxf;

import com.zxf.bean.Experience;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ExperienceTest {
    @Test
    public void select() throws Exception{
        try (SqlSession session = MyBatises.openSession()){
            List<Experience> experiences = session.selectList("experience.list");
            for (Experience experience : experiences) {
                System.out.println(experience);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void select1() throws Exception{
        try (SqlSession session = MyBatises.openSession()){
            List<Map<String,Object>> experiences = session.selectList("experience.list");
            for (Map<String, Object> experience : experiences) {
                System.out.println(experience);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
