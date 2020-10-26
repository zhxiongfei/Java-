package com.zxf.util;

import com.zxf.bean.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TplTest {

    private static Class[] classes = {User.class};
    private static final Map<String, String> TPL_DIRS = new HashMap<>();
    private static final String BASE_DIR =
            "/Users/liuxiaoyong/Desktop/Personal/JavaEE_Learn/projects/resume/src/main/java/com/zxf";
    static {
        TPL_DIRS.put("Dao","dao");
        TPL_DIRS.put("DaoImpl","dao/impl");
        TPL_DIRS.put("Service","service");
        TPL_DIRS.put("ServiceImpl","service/impl");
    }

    public static void main(String[] args) throws Exception {

        for (Map.Entry<String,String> entry : TPL_DIRS.entrySet()){
            String suffix = entry.getKey(); // 后缀
            String dir = entry.getValue();
            // 获取tpl文件的路径
            String tpl = "tpl/" + suffix + ".tpl";
            String tplFilePath = TplTest.class.getClassLoader().getResource(tpl).getFile();
            // 模板文件的内容
            String text = FileUtils.readFileToString(new File(tplFilePath), "UTF-8");

            for (Class cls : classes){
                String clsName = cls.getSimpleName();
                String newText = text.replace("#0#", clsName);
                String fileName = clsName + suffix + ".java";
                String filePath = BASE_DIR + "/" + dir + "/" + fileName;

                File file = new File(filePath);
                if (file.exists()){
                    System.out.println("文件已存在 : " + filePath);
                    continue;
                }
                FileUtils.writeStringToFile(new File(filePath), newText, "UTF-8");
                System.out.println("新生成 : " + filePath);
            }
        }
    }
}
