package com.zxf.servlet;

import com.zxf.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonServlet {
//    private static PersonService service = GeneralFactory.get("personService");

    private PersonService service;

    public void setService(PersonService service) {
        this.service = service;
    }

    public void remove(){
        service.remove(1);
    }

    public static void main(String[] args) {
        // 读取配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // context bean工厂
        PersonServlet servlet = context.getBean("personServlet",PersonServlet.class);
        servlet.remove();
    }

    /**
     *
     * IoC : Inversion of Control
     * 控制反转
     * 对象创建的权利反转
     * 对象创建的控制权交给了 IoC容器
     *
     * */

    /**
     * DI,Dependency Injection 依赖注入
     *
     * 比如, Spring 将 dao对象注入到Service中
     *
     * */
}
