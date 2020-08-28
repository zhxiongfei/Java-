package com.zxf.test;

import com.zxf.bean.Person;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ManyToMany {

    @Test
    public void personGet(){
        try (SqlSession session = MyBatises.openSession()){
            Person person = session.selectOne("person.get", 1);
//            System.out.println(person.getIdCard().getId());
            System.out.println(person.getBankCards());
        }
    }
}
