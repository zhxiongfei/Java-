package com.zxf.controller;

import com.zxf.domain.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class JSPController2 {

    @RequestMapping("/jsp1")
    public String jsp1(){
        return "jsp1";
    }

    @RequestMapping("/jsp2")
    public ModelAndView jsp2(){
        return new ModelAndView("jsp2");
    }

    @RequestMapping("/jsp3")
    public ModelAndView jsp3(){
        ModelAndView view = new ModelAndView();
        /**
            InternalResourceView; 转发
            JstlView;             转发
            RedirectView;         重定向
         */
        view.setView(new InternalResourceView("/page/jsp3.jsp"));
        return view;
    }

}
