package com.zxf.controller;

import com.zxf.domain.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class JSPController {

    @RequestMapping("/test/dog/jsp1")
    public ModelAndView jsp1(){
        // 转发jsp页面
        ModelAndView view = new ModelAndView();
        Dog dog = new Dog();
        dog.setName("Hachi");
        dog.setPrice(1000);

        // 设置数据
        view.addObject("dog",dog);

        // 转发
        view.setViewName("forward:/WEB-INF/page2/jsp1.jsp");
        return view;
    }

    @RequestMapping("/jsp2")
    public String jsp2(HttpServletRequest request){

        Dog dog = new Dog();
        dog.setName("Hachi");
        dog.setPrice(1000);
        request.setAttribute("dog",dog);

        return "/page/jsp2.jsp";
    }

    @RequestMapping("/jsp3")
    public String jsp3(){
        return "redirect:/page/jsp3.jsp";
    }

    @RequestMapping("/jsp4")
    public ModelAndView jsp4(){
        ModelAndView view = new ModelAndView();

        Dog dog = new Dog();
        dog.setName("Hachi");
        dog.setPrice(1000);
        // 设置数据
//        view.addObject("dog",dog);

        view.addObject("name","Hachi");
        view.addObject("price",1000);

        view.setViewName("redirect:/page/jsp4.jsp");
        return view;
    }

//    @RequestMapping("/jsp5")
//    public String jsp5(){
//        return "/WEB-INF/page/jsp3.jsp";
//    }
}
