package com.zxf.servlet;

import com.zxf.bean.Company;
import com.zxf.bean.Education;
import com.zxf.service.CompanyService;
import com.zxf.service.CompanyServiceImpl;
import com.zxf.service.EducationService;
import com.zxf.service.EducationServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/company/*")
public class CompanyServlet extends BaseServlet {

    private CompanyService service = new CompanyServiceImpl();
    /**
     * 获取教育列表
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("companies", service.list());
        request.getRequestDispatcher("/WEB-INF/page/admin/company.jsp").forward(request,response);
    }

    /**
     * 添加 / 保存
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Company bean = new Company();
        BeanUtils.populate(bean, request.getParameterMap());

        if (service.save(bean)){
            // 保存成功
            // 重定向的 company
            response.sendRedirect(request.getContextPath() + "/company/admin");
        }else {
            // 保存失败
            request.setAttribute("error","公司信息保存失败");
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
            response.sendRedirect(request.getContextPath() + "/company/admin");
        }else {
            request.setAttribute("error","公司信息删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }

}

