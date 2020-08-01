package com.zxf.servlet;

import com.zxf.bean.Customer;
import com.zxf.dao.CustomerDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

    private final CustomerDao dao = new CustomerDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String url = request.getRequestURI();
        String[] cmps = url.split("/");
        if (cmps.length == 1) return;
        String methodName = cmps[cmps.length - 1];
        System.out.println(methodName);
        try { // 反射
            Method method = getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询列表
     * */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> list = dao.list();
        request.setAttribute("customers",dao.list());
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }

    /**
     * 保存数据
     * */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取客户端发送的参数
        Customer customer = new Customer();
        BeanUtils.populate(customer, request.getParameterMap());

        // 存储到数据库=
        if (dao.save(customer)) {
            // 重定向
            response.sendRedirect("/crm/customer/list");
        } else {
            forawrdError(request, response, "保存客户信息失败");
        }
    }

    /**
     * 跳转编辑页面
     * */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Customer customer = dao.find(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/page/update.jsp").forward(request, response);
    }

    /**
     * 修改数据
     * */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取客户端发送的参数
        Customer customer = new Customer();
        BeanUtils.populate(customer, request.getParameterMap());

        // 更新
        if (dao.update(customer)) {
            // 重定向
            response.sendRedirect("/crm/customer/list");
        } else {
            forawrdError(request, response, "更新客户信息失败");
        }
    }

    /**
     * 删除
     * */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        if (dao.remove(id)) {
            // 重定向
            response.sendRedirect("/crm/customer/list");
        } else {
            forawrdError(request, response, "删除客户信息失败");
        }
    }

    private void forawrdError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher("/page/error.jsp").forward(request, response);
    }
}
