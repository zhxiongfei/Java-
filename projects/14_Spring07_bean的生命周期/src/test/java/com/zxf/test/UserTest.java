package com.zxf.test;

import com.zxf.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void test() {
        // 创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService service = context.getBean("userService",UserService.class);
        service.login("123","456");
        // 关闭容器
        context.close();
    }
}
