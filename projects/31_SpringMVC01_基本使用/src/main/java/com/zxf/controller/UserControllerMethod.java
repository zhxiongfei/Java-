package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserControllerMethod {

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PostMapping("/add")
    @ResponseBody
    public String add(){
        return "ADD SUCCESS 2";
    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @GetMapping("/get")
    @ResponseBody
    public String get(){
        return "GET SUCCESS 2";
    }

}
