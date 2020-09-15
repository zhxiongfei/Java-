package com.zxf.resolver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice(basePackages = "com.zxf.controller")
public class MyExceptionResolver1 {

    @ExceptionHandler({IOException.class, ClassCastException.class})
    public ModelAndView resolveException1(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.addObject("name", "zxf");
        mv.setViewName("/WEB-INF/page/error/io.jsp");
        return mv;
    }

    @ExceptionHandler({ArithmeticException.class, ClassNotFoundException.class})
    public ModelAndView resolveException2(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.addObject("name", "zxf");
        mv.setViewName("/WEB-INF/page/error/default.jsp");
        return mv;
    }

}
