package com.zxf.servlet;
import com.zxf.bean.Award;
import com.zxf.bean.UploadParams;
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
        UploadParams uploadParams = Uploads.parseRequest(request);
        Award bean = new Award();
        BeanUtils.populate(bean, uploadParams.getParams());
        FileItem item = uploadParams.getFileParam("imageFile");
        bean.setImage(Uploads.uploadImage(item, request, bean.getImage()));

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