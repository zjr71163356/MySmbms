package com.gzw.servlet.user;

import com.gzw.pojo.User;
import com.gzw.service.user.UserService;
import com.gzw.service.user.UserServiceImpl;
import com.gzw.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet....");
        // 获取客户端的userName和userPassword
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库中的密码进行对比，调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        // 检测有没有此人
        if (user!=null){
            // 将用户的信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            req.getSession().setAttribute("userId",user.getId());
            // 跳转到网站内部
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            // 没有则转发会登录页面并提示用户名或者密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
