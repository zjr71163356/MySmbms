package com.gzw.servlet.provider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

}
private  void add(HttpServletRequest req,HttpServletResponse resp)
{

}

}
