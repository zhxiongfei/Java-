package com.zxf.servlet;

import com.zxf.bean.Website;
import com.zxf.dao.WebsiteDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/website/*")
public class WebsiteServlet extends BaseServlet {

    WebsiteDao dao = new WebsiteDao();
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Website website = (dao.list() == null) ? null : dao.list().get(0);
        request.setAttribute("website" , website);

        // 转发
        request.getRequestDispatcher("/page/admin/website.jsp").forward(request, response);
    }

}
