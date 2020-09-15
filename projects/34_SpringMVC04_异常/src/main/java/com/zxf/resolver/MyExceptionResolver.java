package com.zxf.resolver;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception ex) {

        HandlerMethod method = (HandlerMethod)handler;
        method.getBean();
        method.getMethod();

        ModelAndView mv = new ModelAndView();
        mv.addObject("ex", ex);
        mv.addObject("name", "zxf");
        mv.setViewName("/WEB-INF/page/error/default.jsp");

        return mv;

    }
}
