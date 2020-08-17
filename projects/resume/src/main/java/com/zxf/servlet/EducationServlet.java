package com.zxf.servlet;

import com.zxf.bean.Education;
import com.zxf.service.EducationService;
import com.zxf.service.EducationServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/education/*")
public class EducationServlet extends BaseServlet {

    private EducationService service = new EducationServiceImpl();
    /**
    * 获取教育列表
    * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("educations", service.list());
        request.getRequestDispatcher("/WEB-INF/page/admin/education.jsp").forward(request,response);
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
            response.sendRedirect(request.getContextPath() + "/education/admin");
        }else {
            // 保存失败
            request.setAttribute("error","教育信息保存失败");
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
            System.out.println(id);
        }
        if (service.remove(ids)){
            response.sendRedirect(request.getContextPath() + "/education/admin");
        }else {
            request.setAttribute("error","教育信息删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }


}
