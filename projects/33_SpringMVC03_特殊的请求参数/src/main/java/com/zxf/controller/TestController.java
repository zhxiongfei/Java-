package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String name, Integer age){
        System.out.println(name);
        System.out.println(age);
        return "SUCCESS!";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam Map<String,Object> map){
        System.out.println(map);
        return "SUCCESS!";
    }

}
