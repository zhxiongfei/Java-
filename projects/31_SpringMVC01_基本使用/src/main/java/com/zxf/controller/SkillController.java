package com.zxf.controller;

import com.zxf.domain.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/skill")
public class SkillController {

    @RequestMapping("/add")
    @ResponseBody
    public String add(Skill skill){
        System.out.println(skill);
        return "SKILL ADD SUCCESS";
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable("id")Integer id){
        return "SKILL GET SUCCESS " + id;
    }

}
