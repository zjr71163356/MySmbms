//package com.gzw.servlet.user;
//
//import com.gzw.service.user.UserService;
//import com.gzw.service.user.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.text.ParseException;
//
////用户注册的Servlet
//public class RegisterServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //以下是必选信息
//        String userCode = req.getParameter("userCode");
//        String userPassword = req.getParameter("userPassword");
//        String userPassword2 = req.getParameter("userPassword2");
//        //以下是可选信息 他们在jsp中的name值都一致才能使用getParameterValues()
//        Object[] obj=req.getParameterValues("options");
//        UserServiceImpl userService = new UserServiceImpl();
//        HttpSession session = req.getSession();
//
//        if (userService.checkUserCode(userCode) == true && userCode != null) {
//
//            if (!userPassword.equals(userPassword2) || userPassword == null || userPassword2 == null)
//                session.setAttribute("message", "密码不一致或为空，请重新输入");
//            else userService.registUser(userCode, userPassword,obj);
//        }
//        else session.setAttribute("message", "用户名重复或为空，请重新输入");
//
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
//    }
//
//}
