package com.zxf.servlet;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.zxf.bean.UploadParams;
import com.zxf.bean.User;
import com.zxf.service.AwardService;
import com.zxf.service.SkillService;
import com.zxf.service.UserService;
import com.zxf.service.WebsiteService;
import com.zxf.service.impl.AwardServiceImpl;
import com.zxf.service.impl.SkillServiceImpl;
import com.zxf.service.impl.WebsiteServiceImpl;
import com.zxf.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet<User> {

    private SkillService skillService = new SkillServiceImpl();
    private AwardService awardService = new AwardServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String[] cmps = uri.split("/");
        String methodName = "/" + cmps[cmps.length - 1];
        if (methodName.equals(request.getContextPath())){
            try {
                front(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            super.doGet(request, response);
        }
    }

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = service.list().get(0);
        request.setAttribute("user",user);
        forward(request,response,"admin/user.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadParams uploadParams = Uploads.parseRequest(request);
        User user = new User();
        BeanUtils.populate(user,uploadParams.getParams());
        User loginUser = (User)request.getSession().getAttribute("user");
        user.setPassword(loginUser.getPassword());
        user.setEmail(loginUser.getEmail());

        FileItem item = uploadParams.getFileParam("photo");
        user.setPhoto(Uploads.uploadImage(item, request, user.getPhone()));

        if (service.save(user)) {
            // 登录成功后，将user对象保存在session中
            request.getSession().setAttribute("user", user);
            // 保存成功 重定向
            redirect(request,response,"user/admin");
        } else {
            // 保存失败
            forwardError(request, response, "用户信息保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception{
    }

    /**
     * 登录
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 检查验证码
        String captcha = request.getParameter("captcha").toLowerCase();
        HttpSession session = request.getSession();
        if (!captcha.equals(session.getAttribute("code"))){
            forwardError(request,response,"验证码错误");
            return;
        }

        // 检查用户名、密码
        User user = new User();
        BeanUtils.populate(user, request.getParameterMap());

        List<User> users = ((UserService) service).get(user);
        if (users == null || users.isEmpty()){
            // 登录失败
            forwardError(request,response,"用户名或密码错误");
        }else {
            // 登录成功
            // 将user对象放到session中
            session.setAttribute("user",users.get(0));
            redirect(request,response,"user/admin");
        }
    }

    /**
     * 退出登录
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 清除登录信息 (将session中的信息清除)
        request.getSession().removeAttribute("user");
        // 重定向到登录页面
        redirect(request,response,"page/login.jsp");
    }

    /**
     * 验证码
     * */
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 创建Katpcha对象
        DefaultKaptcha dk = new DefaultKaptcha();

        // 验证码的配置
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("kaptcha.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            Config config = new Config(properties);
            dk.setConfig(config);
        }

        // 生成验证码字符串
        String code = dk.createText();

        // code 存储到session
        // 第一次使用session时，会创建session
        // 再次使用会返回创建好的session
        HttpSession session = request.getSession();
        session.setAttribute("code",code.toLowerCase());

        // 生成验证码图片
        BufferedImage image = dk.createImage(code);

        // 设置返回数据的格式
        response.setContentType("image/jpeg");

        // 将图片数据写会到客户端
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    /**
     * 修改密码页面
     */
    public void password(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 转发到修改密码页面
        forward(request,response,"admin/password.jsp");
    }

    /**
     * 更改密码
     * */
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldPassword = request.getParameter("oldPassword");
        // 对比session中用户的密码
        User user = (User)request.getSession().getAttribute("user");
        if (!user.getPassword().equals(oldPassword)){
            forwardError(request,response,"旧密码不正确");
            return;
        }
        // 保存新密码
        String newPassword = request.getParameter("newPassword");
        user.setPassword(newPassword);
        if (service.save(user)){
            redirect(request,response,"page/login.jsp");
        } else {
            forwardError(request,response,"修改密码失败");
        }
    }

    /**
     * 用户简历前台页面
     * */
    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 用户信息
        User user = service.list().get(0);
        request.setAttribute("user",user);

        // 个人特质
        request.setAttribute("trait",user.getTrait().split(","));

        // 兴趣爱好
        request.setAttribute("interests", user.getInterests().split(","));

        // 专业技能
        request.setAttribute("skills", skillService.list());

        // 获奖成就
        request.setAttribute("awards", awardService.list());

        // 网站底部信息
        request.setAttribute("footer", websiteService.list().get(0).getFooter());

        forward(request,response,"front/user.jsp");
    }
}
