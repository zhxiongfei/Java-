package com.zxf.servlet;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public class BaseServlet extends HttpServlet {

    static {
        // null参数表示允许值为null
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(new String[]{"yyyy-MM-dd", "yyyy"});
        ConvertUtils.register(dateConverter, Date.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 设置编码
            request.setCharacterEncoding("UTF-8");

            // 利用方法名调用方法
            String uri = request.getRequestURI();
            String[] cmps = uri.split("/");
            String methodName = cmps[cmps.length - 1];

            Method method = getClass()
                    .getMethod(methodName,
                            HttpServletRequest.class,
                            HttpServletResponse.class);

            // 调用方法
            method.invoke(this, request, response);
        }catch (Exception e) {
            e.printStackTrace();

            Throwable cause = e;
            while (cause.getCause() != null) cause = cause.getCause();
            request.setAttribute("error",cause.getClass().getName() + ": " + cause.getMessage());
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request,response);
        }
    }
}
