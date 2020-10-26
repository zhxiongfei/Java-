package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/encoding")
public class EncodingController {

    @RequestMapping("/login")
    @ResponseBody
    public String add(String username, String password){
        System.out.println(username);
        System.out.println(password);
        return "LOGIN SUCCESS";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get(){
        return "大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭大哭";
    }

}
