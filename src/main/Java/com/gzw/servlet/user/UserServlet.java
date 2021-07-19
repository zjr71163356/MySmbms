package com.gzw.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.gzw.pojo.Role;
import com.gzw.pojo.User;
import com.gzw.service.role.RoleServiceImpl;
import com.gzw.service.user.UserService;
import com.gzw.service.user.UserServiceImpl;
import com.gzw.util.Constants;
import com.gzw.util.PageSupport;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getParameter("method");
        if (method.equals("savepwd")&&method!=null){
            this.updatePwd(req,resp);
        }else if (method.equals("pwdmodify")&&method!=null){
            this.pwdModify(req,resp);
        }else if (method.equals("query")){
            this.query(req,resp);
        }

    }

    // 查询用户列表
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从前端获取数据
        String queryNameInfo = req.getParameter("queryname");
        String queryUserRoleInfo = req.getParameter("queryUserRole");
        String pageIndexInfo = req.getParameter("pageIndex");

        List<User> userList;

        // 默认为0
        int queryUserRole = 0;
        // 默认为第一页
        int currentPageNo = 1;
        // 第一页默认size为5
        int pageSize = 5;

        // 获取用户列表
        UserServiceImpl userService = new UserServiceImpl();

        if (queryNameInfo == null){
            queryNameInfo = "";
        }
        if (queryUserRoleInfo != null && queryUserRoleInfo.equals("")){
            queryUserRole = Integer.parseInt(queryUserRoleInfo);
        }
        if (pageIndexInfo!=null){
            currentPageNo = Integer.parseInt(pageIndexInfo);
        }
        // 获取用户总数
        int totalCount = userService.getUserCount(queryNameInfo, queryUserRole);

        // 总页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        // 控制首页和尾页
        int totalPageCount = (int) (totalCount/pageSize + 1);
        if (currentPageNo<1){
            currentPageNo = 1;
        }else if (currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }

        // 获取用户列表
        userList = userService.getUserList(queryNameInfo, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);
        // 获取角色列表
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();

        // 给前端传值
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryUserName",queryNameInfo);
        req.setAttribute("queryUserRole",queryUserRoleInfo);


        // 返回前端页面
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException { doGet(req, resp);
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
