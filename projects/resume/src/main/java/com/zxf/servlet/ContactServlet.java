package com.zxf.servlet;

import com.zxf.bean.Contact;
import com.zxf.service.UserService;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.UserServiceImpl;
import com.zxf.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/contact/*")
public class ContactServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    /**
     *
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Contact> list = service.list();
        request.setAttribute("contact",service.list());
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
     * 前台页面
     * */
    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("user", userService.list().get(0));
        request.setAttribute("footer", websiteService.list().get(0).getFooter());

        // 转发
        forward(request,response,"front/contact.jsp");
    }


}
