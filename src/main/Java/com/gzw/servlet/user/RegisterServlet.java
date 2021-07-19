package com.gzw.servlet.user;

import com.gzw.service.user.UserService;
import com.gzw.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        String userPassword2 = req.getParameter("userPassword2");
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = req.getSession();

        if (userService.checkUserCode(userCode) == true || userCode != null) {

            if (!userPassword.equals(userPassword2) || userPassword == null || userPassword2 == null)
                session.setAttribute("message", "密码不一致或为空，请重新输入");
            else userService.registUser(userCode, userPassword);
        }
        else session.setAttribute("message", "用户名重复或为空，请重新输入");

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

}
