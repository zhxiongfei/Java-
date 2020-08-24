package com.zxf.servlet;

import com.zxf.bean.Company;
import com.zxf.bean.Project;
import com.zxf.bean.UploadParams;
import com.zxf.service.CompanyService;
import com.zxf.service.UserService;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.CompanyServiceImpl;
import com.zxf.service.impl.UserServiceImpl;
import com.zxf.service.impl.WebsiteServiceImpl;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contact")
public class ContactServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    /**
     *
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    /**
     * 添加留言
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{

    }

    /**
     * 删除
     * */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception{

    }

    /**
     * 前台页面
     * */
    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("user", userService.list().get(0));
        request.setAttribute("footer", websiteService.list().get(0).getFooter());

        // 转发
        forward(request,response,"front/contact.jsp");
    }


}
