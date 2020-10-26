package com.zxf.util;

import com.zxf.bean.UploadParams;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Uploads {
    private static final String BASE_DIR = "upload";
    private static final String IMG_DIR = "img";

    /**
     * 图片上传
     * @param item 文件参数
     * @param request 请求
     * @param oldImage 以前的图片名
     * @return 存储到数据库的图片路径
     * @throws Exception
     */
    public static String uploadImage(FileItem item, HttpServletRequest request, String oldImage) throws Exception{
        if (oldImage == null || oldImage.length() == 0) oldImage = null;
        if (item == null) return oldImage;
        InputStream is = item.getInputStream();
        if (is.available() == 0) return oldImage;

        ServletContext ctx = request.getServletContext();

        // 保存新文件
        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(item.getName());
        String image = BASE_DIR + "/" + IMG_DIR + "/" + filename;
        String filePath = ctx.getRealPath(image);
        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(filePath));

        // 删除旧文件
        // 如果oldImage是空串，就会把整个项目删掉
        if (oldImage != null && oldImage.length() > 0) FileUtils.deleteQuietly(new File(ctx.getRealPath(oldImage)));
        return image;
    }

    public static UploadParams parseRequest(HttpServletRequest request) throws Exception {
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
        UploadParams uploadParams = new UploadParams();
        uploadParams.setParams(params);
        uploadParams.setFileParams(fileParams);
        return uploadParams;
    }


}
