package com.zxf;

import com.zxf.bean.Job;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class JobTest {
    @Test
    public void firstLevel() {
        try(SqlSession session = MyBatises.openSession()){
            Job job1 = session.selectOne("job.get",1);

            session.update("job.update", job1);
            Job job2 = session.selectOne("job.get",1);

            System.out.println(job1);
            System.out.println(job2);
        }
    }

    @Test
    public void secondLevel() {
        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }

        try(SqlSession session = MyBatises.openSession()){
            Job job = session.selectOne("job.get",1);
            System.out.println(job);
        }
    }
}
