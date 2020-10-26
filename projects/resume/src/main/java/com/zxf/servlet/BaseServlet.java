package com.zxf.servlet;

import com.zxf.dao.BaseDao;
import com.zxf.service.BaseService;
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

public abstract class BaseServlet<T> extends HttpServlet {

    static {
        // null参数表示允许值为null
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(new String[]{"yyyy-MM-dd", "yyyy"});
        ConvertUtils.register(dateConverter, Date.class);
    }

    protected BaseService<T> service = newService();
    protected BaseService<T> newService(){
        String clsName = getClass().getName().replace(".servlet.",".service.impl.")
                .replace("Servlet","ServiceImpl");
        try {
            return (BaseService<T>) Class.forName(clsName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
        } catch (Exception e) {
            e.printStackTrace();

            Throwable cause = e;
            while (cause.getCause() != null) cause = cause.getCause();
            forwardError(request, response, cause.getClass().getName() + ": " + cause.getMessage());
        }
    }

    /**
     * 转发
     *
     * @param request  request
     * @param response response
     * @param path     转发路径
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/page/" + path).forward(request, response);
    }

    /**
     * 转发到错误页面
     *
     * @param request  request
     * @param response response
     * @param error    错误信息
     * @throws ServletException
     * @throws IOException
     */
    protected void forwardError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        request.setAttribute("error", error);
        forward(request, response, "error.jsp");
    }

    /**
     * 重定向
     * @param request request
     * @param response response
     * @param path 重定向路径
     * @throws ServletException
     * @throws IOException
     */
    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/" + path);
    }

}
