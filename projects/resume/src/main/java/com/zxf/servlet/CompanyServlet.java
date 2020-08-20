package com.zxf.servlet;

import com.zxf.bean.Company;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/company/*")
public class CompanyServlet extends BaseServlet<Company> {

    /**
     * 获取公司
     * */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Company>list = service.list();
        request.setAttribute("companies", list);
        forward(request,response,"admin/company.jsp");
    }

    /**
     * 添加 / 保存
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception{

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = upload.parseRequest(request);

        // 非文件参数
        Map<String,Object> params = new HashMap<>();

        // 文件参数
        Map<String,FileItem> fileParams = new HashMap<>();

        // 遍历请求参数
        for (FileItem item : items){
            String fileName = item.getFieldName();
            if (item.isFormField()){ // 非文件类型
                params.put(fileName, item.getString("UTF-8"));
            }else { // 文件类型
                // 图片在硬盘上的存放路径
                fileParams.put(fileName, item);
            }
        }

        Company bean = new Company();
        BeanUtils.populate(bean, params);
        FileItem item = fileParams.get("logoFile");
        bean.setLogo(Uploads.uploadImage(item, request, bean.getLogo()));

        // 如果图片是空字符串，就存储null
        if (bean.getLogo() != null && bean.getLogo().length() == 0){
            bean.setLogo(null);
        }

        if (service.save(bean)){
            // 保存成功
            // 重定向的 company
            redirect(request,response,"company/admin");
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
            redirect(request,response,"company/admin");
        }else {
            forwardError(request,response, "公司信息删除失败");
        }
    }

}

