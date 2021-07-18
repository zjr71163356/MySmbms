package com.gzw.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.gzw.pojo.User;
import com.gzw.service.user.UserService;
import com.gzw.service.user.UserServiceImpl;
import com.gzw.util.Constants;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd")&&method!=null){
            this.updatePwd(req,resp);
        }else if (method.equals("pwdmodify")&&method!=null){
            this.pwdModify(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doGet(req, resp);
    }


    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    // 验证旧密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        // 万能的map,原生结果集
        Map<String, String> resultMap = new HashMap<String, String>();

        // o==null表示session过期
        if (o==null){
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){
            // 密码为空
            resultMap.put("result","error");
        }else {
            String userPassword = ((User) o).getUserPassword();
            if (oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(JSONArray.toJSONString(resultMap));
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
