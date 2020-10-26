package com.zxf.test;

import com.zxf.bean.IdCard;
import com.zxf.bean.Person;
import com.zxf.utils.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class OneToOne {
    @Test
    public void personList(){
        try (SqlSession session = MyBatises.openSession()){
            List<Person> persons = session.selectList("person.list");
            System.out.println(persons);
        }
    }

    @Test
    public void personGet(){
        try (SqlSession session = MyBatises.openSession()){
            Person person = session.selectOne("person.get", 1);
            System.out.println(person);
        }
    }

    @Test
    public void idCardList(){
        try (SqlSession session = MyBatises.openSession()){
            List<IdCard> cards = session.selectList("idCard.list");
            System.out.println(cards);
        }
    }

    @Test
    public void cardsGet(){
        try (SqlSession session = MyBatises.openSession()){
            IdCard card = session.selectOne("idCard.get", 1);
            System.out.println(card);
        }
    }
}
