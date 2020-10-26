package com.zxf.servlet;

import com.zxf.bean.Website;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/website/*")
public class WebsiteServlet extends BaseServlet<Website> {

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Website website = (service.list() == null) ? null : service.list().get(0);
        request.setAttribute("website" , website);

        // 转发
        forward(request,response,"admin/website.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Website website = new Website();
        BeanUtils.populate(website, request.getParameterMap());
        if (service.save(website)){
            // 保存成功
            // 重定向到 admin
            redirect(request, response, "website/admin");
        }else {
            // 保存失败
            forwardError(request,response,"网站信息保存失败");
        }
    }

}
