package com.zxf.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam Map<String,Object> map){
        System.out.println(map);
        return "SUCCESS!";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String name, Integer age){
        System.out.println(name);
        System.out.println(age);
        return "SUCCESS!";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(String username, String password){
        System.out.println(username);
        System.out.println(password);
        return "test2 SUCCESS";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String test3(String username, MultipartFile photo,HttpServletRequest request) throws Exception {

        // 将文件数据写入到具体位置
        String filename = photo.getOriginalFilename();
        String path = request.getServletContext().getRealPath("upload/img/" + filename);
        File file = new File(path);
        photo.transferTo(file);
        return "test3 SUCCESS";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String test4(String username, MultipartFile photo1,MultipartFile photo2,HttpServletRequest request) throws Exception {

        // 将文件数据写入到具体位置
        String filename = photo1.getOriginalFilename();
        String path = request.getServletContext().getRealPath("upload/img/" + filename);
        File file = new File(path);
        photo1.transferTo(file);

        // 将文件数据写入到具体位置
        filename = photo2.getOriginalFilename();
        path = request.getServletContext().getRealPath("upload/img/" + filename);
        file = new File(path);
        photo2.transferTo(file);

        return "test4 SUCCESS";
    }

    @RequestMapping("/test5")
    @ResponseBody
    public String test5(String username, MultipartFile[] photos,HttpServletRequest request) throws Exception {
        for (MultipartFile photo : photos) {
            System.out.println(photo.getOriginalFilename());
        }
        return "test5 SUCCESS";
    }

//    @RequestMapping("/test6")
//    @ResponseBody
//    public String test6(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday) throws Exception {
//        System.out.println(birthday);
//        return "test6 SUCCESS";
//    }

    @RequestMapping("/test6")
    @ResponseBody
    public String test6(Date birthday) throws Exception {
        System.out.println(birthday);
        return "test6 SUCCESS";
    }

}
