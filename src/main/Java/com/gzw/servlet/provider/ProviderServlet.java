package com.gzw.servlet.provider;

import com.gzw.pojo.Provider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("query")&&method!=null){
//                this.query(req,resp);
            req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
        }
        if(method.equals("add")&& method!=null){
            req.getRequestDispatcher("provideradd.jsp").forward(req,resp);
        }
    }
private  void query(HttpServletRequest req,HttpServletResponse resp)
{
    // 默认为0
    // 默认为第一页
    int currentPageNo = 1;
    // 第一页默认size为5
    int pageSize = 5;

    String queryProCode = req.getParameter("queryProCode");
    String queryProName = req.getParameter("queryProName");
    List<Provider> providerList=new ArrayList<Provider>();



}
private  void add(HttpServletRequest req,HttpServletResponse resp)
{

}

}
