package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/addUser")
    @ResponseBody
    public String add(){
        return "ADD SUCCESS";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        return "GET SUCCESS";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(){
        return "REMOVE SUCCESS";
    }
}
