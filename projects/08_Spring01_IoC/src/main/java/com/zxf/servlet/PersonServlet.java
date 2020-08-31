package com.zxf.servlet;

import com.zxf.factory.GeneralFactory;
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

        PersonServlet servlet = context.getBean("personServlet",PersonServlet.class);

        servlet.remove();
    }
}
