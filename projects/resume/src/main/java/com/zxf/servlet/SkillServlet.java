package com.zxf.servlet;

import com.zxf.bean.Skill;
import com.zxf.bean.Website;
import com.zxf.service.SkillService;
import com.zxf.service.SkillServiceImpl;
import com.zxf.service.WebsiteService;
import com.zxf.service.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/skill/*")
public class SkillServlet extends BaseServlet {

    private SkillService service = new SkillServiceImpl();
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Skill skill = (service.list() == null) ? null : service.list().get(0);
        request.setAttribute("skills" , skill);

        // 转发
        request.getRequestDispatcher("/WEB-INF/page/admin/skill.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Skill skill = new Skill();
        BeanUtils.populate(skill, request.getParameterMap());
        if (service.save(skill)){
            // 保存成功
            // 重定向到 admin
            response.sendRedirect(request.getContextPath() + "/skill/admin");
        }else {
            // 保存失败
            request.setAttribute("error","技能信息保存失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }

}