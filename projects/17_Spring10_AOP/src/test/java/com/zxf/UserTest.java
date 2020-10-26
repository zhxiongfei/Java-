package com.zxf;

import com.zxf.service.SkillService;
import com.zxf.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService us = context.getBean("userService",UserService.class);
        us.login("","");

        SkillService ss = context.getBean("skillService", SkillService.class);
        ss.save(null);
    }
}
