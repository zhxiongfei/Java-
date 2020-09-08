package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//@RequestMapping("/user")
public class UserController2 {

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        return "ADD SUCCESS 2";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        return "GET SUCCESS 2";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(){
        return "REMOVE SUCCESS 2";
    }

}
