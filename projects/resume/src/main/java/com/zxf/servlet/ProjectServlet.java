package com.zxf.servlet;

import com.zxf.bean.Company;
import com.zxf.bean.Project;
import com.zxf.bean.UploadParams;
import com.zxf.service.CompanyService;
import com.zxf.service.impl.CompanyServiceImpl;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/project/*")
public class ProjectServlet extends BaseServlet<Project> {

    private CompanyService companyService = new CompanyServiceImpl();
    /**
     * 获取项目经验
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("projects", service.list());
        request.setAttribute("companies", companyService.list());
        forward(request,response,"admin/project.jsp");
    }

    /**
     * 添加 / 保存
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{

        UploadParams uploadParams = Uploads.parseRequest(request);
        Project bean = new Project();
        BeanUtils.populate(bean, uploadParams.getParams());

        // 对公司信息做特殊处理
        Company company = new Company();
        company.setId(Integer.valueOf(uploadParams.getParam("companyId").toString()));

        bean.setCompany(company);

        FileItem item = uploadParams.getFileParams().get("imageFile");
        bean.setImage(Uploads.uploadImage(item, request, bean.getImage()));

        if (service.save(bean)){
            // 保存成功
            // 重定向的 company
            redirect(request,response,"project/admin");
        }else {
            // 保存失败
            forwardError(request,response, "公司信息保存失败");
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
            redirect(request,response,"project/admin");
        }else {
            forwardError(request,response, "公司信息删除失败");
        }
    }

}

