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
    public void test3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SkillService service = context.getBean("skillService",SkillService.class);
        service.save(null);
    }


    @Test
    public void test2() {
        // 创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 目标对象
        UserService target = context.getBean("userService",UserService.class);

        target.login("133","345");
        target.register("124","345");
    }

    @Test
    public void test() {
        // 创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 目标对象
        UserService target = context.getBean("userService",UserService.class);

        // 反射 - 创建 目标对象 的 代理对象
        UserService userProxy = (UserService) Proxy.newProxyInstance(getClass().getClassLoader(),  // classloader
                target.getClass().getInterfaces(),                              // 代理类需要实现的接口
                (Object proxy, Method method, Object[] args) -> {
                    // proxy - 代理对象
                    // method - 目标方法
                    // args - 目标方法的参数
                    System.out.println("proxy --- 1");

                    // 调用目标对象的目标方法 (核心业务方法)
                    Object res = method.invoke(target, args);

                    System.out.println("proxy --- 2");
                    return res;
                });

        userProxy.register("123","456");

//        SkillService skillService = context.getBean("skillService",SkillService.class);
//        skillService.save(null);

        context.close();
    }
}
