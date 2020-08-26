package com.zxf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 请求之前的处理
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String uri = request.getRequestURI();
        if (!uri.contains("/asset/") && request.getSession().getAttribute("user") == null &&
                (uri.contains("/admin") || uri.contains("/user/password"))){
            response.sendRedirect(request.getContextPath() + "/page/login.jsp");
            return;
        }

        // 转发给服务器处理请求
        chain.doFilter(req, resp);

        // 服务器响应后的处理
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
