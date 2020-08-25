package com.zxf.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxf.bean.Contact;
import com.zxf.bean.ContactListParam;
import com.zxf.bean.ContactListResult;
import com.zxf.service.ContactService;
import com.zxf.service.UserService;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.UserServiceImpl;
import com.zxf.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/contact/*")
public class ContactServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    /**
     *
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContactListParam param = new ContactListParam();
        BeanUtils.populate(param, request.getParameterMap());

        ContactListResult res = ((ContactService) service).list(param);

        request.setAttribute("result",((ContactService) service).list(param));
        // 转发
        forward(request,response,"admin/contact.jsp");
    }

    /**
     * 添加留言
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String code = (String)request.getSession().getAttribute("code");
        String captcha = request.getParameter("captcha");
        if (!code.equals(captcha)){
            forwardError(request, response, "验证码错误");
            return;
        }

        Contact contact = new Contact();
        BeanUtils.populate(contact, request.getParameterMap());

        if (service.save(contact)){
            // 保存成功
            // 重定向的 contact
            redirect(request, response, "contact/front");
        }else {
            // 保存失败
            forwardError(request, response, "留言信息保存失败");
        }
    }

    /**
     * AJAX 异步请求
     * 已读
     * */
    public void read(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Map<String,Object> result = new HashMap<>();
        if (((ContactService)service).read(id)){
            result.put("success",true);
            result.put("msg","更新成功");
        }else {
            result.put("success",false);
            result.put("msg","更新失败");
        }
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
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
