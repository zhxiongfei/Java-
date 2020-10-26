package com.zxf.servlet;

import com.zxf.bean.Skill;
import com.zxf.service.SkillService;
import com.zxf.service.impl.SkillServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/skill/*")
public class SkillServlet extends BaseServlet<Skill> {

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Skill> list = service.list();
        request.setAttribute("skills" , list);

        // 转发
        forward(request,response,"admin/skill.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Skill skill = new Skill();
        BeanUtils.populate(skill, request.getParameterMap());
        if (service.save(skill)){
            // 保存成功
            // 重定向到 admin
            redirect(request, response, "skill/admin");
        }else {
            // 保存失败
            forwardError(request,response,"技能信息保存失败");
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
            redirect(request, response, "skill/admin");
        }else {
            forwardError(request,response,"专业技能信息删除失败");
        }
    }

}