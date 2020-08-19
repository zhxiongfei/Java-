package com.zxf.servlet;
import com.zxf.bean.Award;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/award/*")
public class AwardServlet extends BaseServlet<Award> {

    /**
     * 获取获奖列表
     */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("awards", service.list());
        forward(request, response,"admin/award.jsp");
    }

    /**
     * 添加 / 保存
     */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        // 一个 file item 代表一个请求参数 文件、非文件数据
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

        Award bean = new Award();
        BeanUtils.populate(bean, params);
        FileItem item = fileParams.get("imageFile");
        bean.setImage(Uploads.uploadImage(item, request, bean.getImage()));

        // 如果图片是空字符串，就存储null
        if (bean.getImage() != null && bean.getImage().length() == 0){
            bean.setImage(null);
        }

        if (service.save(bean)) {
            // 保存成功
            // 重定向的 company
            redirect(request,response,"award/admin");
        } else {
            // 保存失败
            forwardError(request, response, "获奖信息保存失败");
        }
    }

    /**
     * 删除
     */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] strings = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String id : strings) {
            ids.add(Integer.valueOf(id));
        }
        if (service.remove(ids)) {
            redirect(request,response,"award/admin");
        } else {
            forwardError(request, response, "获奖成就删除失败");
        }
    }
}