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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/skill/*")
public class SkillServlet extends BaseServlet {

    private SkillService service = new SkillServiceImpl();
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Skill> list = service.list();
        request.setAttribute("skills" , list);

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

    /**
     * 删除
     * */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String[] strings = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String id : strings){
            ids.add(Integer.valueOf(id));
        }
        if (service.remove(ids)){
            response.sendRedirect(request.getContextPath() + "/skill/admin");
        }else {
            request.setAttribute("error","专业技能信息删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }

}