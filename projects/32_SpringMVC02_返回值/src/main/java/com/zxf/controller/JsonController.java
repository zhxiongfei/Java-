package com.zxf.controller;

import com.zxf.domain.Dog;
import com.zxf.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JsonController {

    @RequestMapping(value = "json1", produces = "application/json")
    @ResponseBody
    public String json1(){
        return "{\"name\":\"小码哥\",\"age\":20,\"car\":{\"name\":\"Jack\",\"price\":100}}";
    }

    @RequestMapping(value = "json2")
    @ResponseBody
    public Student json2(){
        Student student = new Student();
        student.setName("Jack");
        student.setAge(12);
        List<String>names = new ArrayList<>();
        names.add("123");
        names.add("456");
        names.add("678");
        student.setNickNames(names);

        Dog dog = new Dog();
        dog.setName("wangwang");
        dog.setPrice(100);
        student.setDog(dog);

        return student;
    }

    @RequestMapping(value = "json3")
    @ResponseBody
    public List<Student> json3(){
        Student student = new Student();
        student.setName("Jack");
        student.setAge(12);
        List<String>names = new ArrayList<>();
        names.add("123");
        names.add("456");
        names.add("678");
        student.setNickNames(names);

        Dog dog = new Dog();
        dog.setName("wangwang");
        dog.setPrice(100);
        student.setDog(dog);

        List<Student> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(student);
        }
        return res;
    }

}
