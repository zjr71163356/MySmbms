package com.gzw.servlet.user;

import com.gzw.pojo.User;
import com.gzw.service.user.UserService;
import com.gzw.service.user.UserServiceImpl;
import com.gzw.util.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getParameter("method");
        if (method.equals("savepwd")&&method!=null){
            this.updatePwd(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) { doGet(req, resp);
    }


    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        // 从session里拿id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;

        if (attribute!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) attribute).getId(), newpassword);
            if (flag){
                req.setAttribute("message","修改密码成功！请退出，使用新密码登录");
                // 密码修改成功，移除session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","修改密码失败！");
            }
        }else {
            req.setAttribute("message","新密码有问题！");
        }

        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
