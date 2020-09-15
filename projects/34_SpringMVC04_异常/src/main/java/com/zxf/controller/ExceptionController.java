package com.zxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ExceptionController {

    @RequestMapping("/test1")
    public void test1() {
        throw new ArithmeticException("666");
    }

    @RequestMapping("/test2")
    public void test2() throws Exception{
        throw new ClassNotFoundException("777");
    }

    @RequestMapping("/test3")
    public void test3() throws Exception{
        throw new IOException("888");
    }

    @RequestMapping("/test4")
    public void test4() throws Exception{
        throw new ClassCastException("999");
    }

//    @ExceptionHandler({IOException.class, ClassCastException.class})
//    public ModelAndView resolveException1(Exception ex){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("ex", ex);
//        mv.addObject("name", "zxf");
//        mv.setViewName("/WEB-INF/page/error/io.jsp");
//        return mv;
//    }
//
//    @ExceptionHandler({ArithmeticException.class, ClassNotFoundException.class})
//    public ModelAndView resolveException2(Exception ex){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("ex", ex);
//        mv.addObject("name", "zxf");
//        mv.setViewName("/WEB-INF/page/error/default.jsp");
//        return mv;
//    }
}
