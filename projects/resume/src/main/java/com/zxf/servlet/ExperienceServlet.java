package com.zxf.servlet;
import com.zxf.bean.Company;
import com.zxf.bean.Experience;
import com.zxf.service.CompanyService;
import com.zxf.service.impl.CompanyServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/experience/*")
public class ExperienceServlet extends BaseServlet<Experience> {

    private CompanyService companyService = new CompanyServiceImpl();

    /**
     * 获取工作经验列表
     */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Experience> list = service.list();
        request.setAttribute("experiences", service.list());
        request.setAttribute("companies", companyService.list());
        forward(request, response,"admin/experience.jsp");
    }

    /**
     * 添加 / 保存
     */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Experience experience = new Experience();
        BeanUtils.populate(experience, request.getParameterMap());

        Company company = new Company();
        company.setId(Integer.valueOf(request.getParameter("companyId")));
        experience.setCompany(company);

        if (service.save(experience)){
            redirect(request,response,"experience/admin");
        }else {
            forwardError(request,response,"工作经验保存失败");
        }
    }

    /**
     * 删除
     */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String id : idStrs) {
            ids.add(Integer.valueOf(id));
        }
        if (service.remove(ids)) {
            redirect(request,response,"experience/admin");
        } else {
            forwardError(request, response, "工作经验删除失败");
        }
    }
}