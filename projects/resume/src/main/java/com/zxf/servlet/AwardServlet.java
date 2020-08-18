package com.zxf.servlet;
import com.zxf.bean.Award;
import com.zxf.service.AwardService;
import com.zxf.service.AwardServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.propertyeditors.InputStreamEditor;

import javax.lang.model.element.NestingKind;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.*;

@WebServlet("/award/*")
public class AwardServlet extends BaseServlet {

    private AwardService service = new AwardServiceImpl();

    /**
     * 获取获奖列表
     */
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("awards", service.list());
        request.getRequestDispatcher("/WEB-INF/page/admin/award.jsp").forward(request, response);
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

        // 存储到数据库的文件路径
        String image = null;
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
        if (item != null) {
            InputStream is = item.getInputStream();
            if (is.available() > 0){
                String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(item.getName());
                image = "/upload/img/" + filename;

                String filePath = request.getServletContext().getRealPath(image);
                FileUtils.copyInputStreamToFile(item.getInputStream(), new File(filePath));

                bean.setImage(image);
            }
        }

        if (service.save(bean)) {
            // 保存成功
            // 重定向的 company
            response.sendRedirect(request.getContextPath() + "/award/admin");
        } else {
            // 保存失败
            request.setAttribute("error", "获奖信息保存失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
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
            response.sendRedirect(request.getContextPath() + "/company/admin");
        } else {
            request.setAttribute("error", "获奖成就删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }
}