package com.zxf.servlet;

import com.zxf.bean.Education;
import com.zxf.service.UserService;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.UserServiceImpl;
import com.zxf.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/education/*")
public class EducationServlet extends BaseServlet<Education> {

    private UserService userService = new UserServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    /**
    * 获取教育列表
    * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("educations", service.list());
        forward(request, response, "admin/education.jsp");
    }

    /**
     * 添加 / 保存
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Education education = new Education();
        System.out.println(request.getParameter("beginDay"));
        BeanUtils.populate(education, request.getParameterMap());

        if (service.save(education)){
            // 保存成功
            // 重定向的 education
            redirect(request, response, "education/admin");
        }else {
            // 保存失败
            forwardError(request, response, "教育信息保存失败");
        }
    }

    /**
     * 删除
     * */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String[] strings = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String id : strings){
            ids.add(Integer.valueOf(id));
            System.out.println(id);
        }
        if (service.remove(ids)){
            redirect(request, response, "education/admin");
        }else {
            forwardError(request, response, "教育信息删除失败");
        }
    }

    /**
     * 前台页面
     * */
    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("user",userService.list().get(0));
        request.setAttribute("footer",websiteService.list().get(0).getFooter());
        request.setAttribute("educations", service.list());
        forward(request,response,"front/education.jsp");
    }



}
