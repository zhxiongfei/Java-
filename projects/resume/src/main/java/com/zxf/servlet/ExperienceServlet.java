package com.zxf.servlet;
import com.zxf.bean.Award;
import com.zxf.bean.Company;
import com.zxf.bean.Experience;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/experience/*")
public class ExperienceServlet extends BaseServlet<Experience> {

    /**
     * 获取工作经验列表
     */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("experiences", service.list());
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