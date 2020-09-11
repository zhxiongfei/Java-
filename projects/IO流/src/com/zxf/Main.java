package com.zxf;

import com.zxf.tools.Files;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Main {

    public static void testOS() throws Exception{
        OutputStream os = new FileOutputStream("../1.txt",true);
//        os.write(77);
//        os.write(74);
        os.write("哈哈哈哈".getBytes());
        os.close();
    }

    public static void testIOS() throws Exception{
        InputStream is = new FileInputStream("../1.txt");
//        System.out.println(Arrays.toString(is.readAllBytes()));

        String string = new String(is.readAllBytes());
        System.out.println(string + "_");

        is.close();
    }

    public static void testWrite() throws Exception{
        String string = "哈哈哈哈ZXF";
        Files.write(string.getBytes(),new File("../a/b/1.txt"));
    }

    public static void testRead() throws Exception{
        File file = new File("../a/b/1.txt");
        String string = new String(Files.read(file));
        System.out.println(string);
    }

    public static void testCopy() throws Exception{
        File src = new File("../apache-tomcat-9.0.34-src.zip");
        File desc = new File("../c/apache-tomcat-9.0.34-src.zip");

        Files.copy(src, desc);
    }

    public static void testBufferWriter() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("../a/b/1.txt"));
        writer.write("123");
        writer.flush();
        writer.close();
    }

    public static void testBufferReader() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("../README.md"))){
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(500);
            }
        }
    }

    public static void testSwitchCoding(){
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("../utf8.txt"), "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("../gbk.txt"), "GBK"));
        ){
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1){
                writer.write(chars, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void test() throws Exception{
        File file = new File("../a/b/1.txt");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream buffer = new BufferedOutputStream(fos);
        buffer.write(97);
        buffer.write(98);
        buffer.flush();
        buffer.close();
    }

    public static void testAI() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null){
            line = line.replace("你","朕");
            line = line.replace("吗","");
            line = line.replace("么","朕");
            line = line.replace("?","!");
            System.out.println("\t" + line);
        }

        reader.close();
    }

    public static void main(String[] args) throws Exception{
        testAI();
    }
}
