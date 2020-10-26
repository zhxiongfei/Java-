package com.zxf.tools;

import java.io.*;
import java.util.function.Consumer;

public class Files {

    /**
     * 复制文件
     * @param src 原地址
     * @param desc 目标地址
     */
    public static void copy(File src, File desc){
        if (src == null || desc == null) return;
        if (!src.exists() || desc.exists()) return;
        if (src.isDirectory()) return;

        mkparents(desc);

        try (InputStream is = new BufferedInputStream(new FileInputStream(src));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(desc))){
            os.write(is.readAllBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 从内存中读取文件
     * @param file 要读取的文件
     * @return
     */
    public static byte[] read(File file){
        if (file == null || !file.exists() || file.isDirectory()) return null;

        try (InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            return fis.readAllBytes();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将内存中的数据写入文件
     * @param bytes
     * @param file
     */
    public static void write(byte[] bytes, File file){
        if (bytes == null || file == null) return;
        if (file.exists()) return;
        mkparents(file);

        try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(file))){
            fos.write(bytes);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除文件 / 文件夹
     * @param file  文件 / 或者文件夹目录
     */
    public static void delete(File file){
        if (file == null || !file.exists()) return;
        if (file.isFile()) {
            file.delete();
        }else {
            clean(file);
        }
    }

    /**
     * 清空文件夹
     * @param dir 文件夹目录
     */
    public static void clean(File dir){
        if (dir == null || !dir.exists() || dir.isFile()) return;
        for (File file : dir.listFiles()) {
            delete(file);
        }
    }

    /**
     * 移动文件
     * @param src  起始位置
     * @param dest 目的位置
     */
    public static void move(File src, File dest){
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        mkparents(dest);
        src.renameTo(dest);
    }

    /**
     * 私有方法 创建父级目录
     * @param dest 目录
     */
    private static void mkparents(File dest){
        File parent = dest.getParentFile();
        if (parent.exists()) return;
        parent.mkdirs();
    }

    /**
     * 搜索
     * @param dir 搜索路径
     * @param operation 结果存放消费者
     */
    public static void search(File dir, Consumer<File> operation){
        if (dir == null || operation == null) return;
        if (!dir.exists() || dir.isFile()) return;

        File[] subFiles = dir.listFiles();
        for (File subFile : subFiles) {
            operation.accept(subFile);

            if (subFile.isFile()) continue;
            search(subFile,operation);
        }
    }
}
