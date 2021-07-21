package com.gzw.servlet.bill;

import com.gzw.pojo.Bill;
import com.gzw.pojo.Provider;
import com.gzw.pojo.Role;
import com.gzw.pojo.User;
import com.gzw.service.bill.BillService;
import com.gzw.service.bill.BillServiceImpl;
import com.gzw.service.provider.ProviderService;
import com.gzw.service.provider.ProviderServiceImpl;
import com.gzw.service.role.RoleServiceImpl;
import com.gzw.service.user.UserServiceImpl;
import com.gzw.util.PageSupport;
import com.mysql.cj.util.StringUtils;
import sun.util.resources.ext.CalendarData_da;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doGet(req, resp); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("query")&&method!=null){
            query(req,resp);
        }else if (method.equals("add")&&method!=null){
            add(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        /*
            获取表单信息
            billCode
            productName
            productUnit
            productCount
            totalPrice
            providerId
            isPayment
        */
        // 创建Bill对象
        Bill bill = new Bill();
        BillServiceImpl billService = new BillServiceImpl();
        // 获取表单信息
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");


    }


    // 查询用户列表
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 回传providerList
        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("","");
        req.setAttribute("providerList",providerList);
        /*
            从前端获取数据
            queryProductName
            queryProviderId
            queryIsPayment
        */
        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");
        List<Bill> billList = null;
        Bill bill = new Bill();
        BillServiceImpl billService = new BillServiceImpl();

        if (StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }else {
            bill.setProductName(queryProductName);
        }

        if (StringUtils.isNullOrEmpty(queryIsPayment)){
            bill.setIsPayment(0);
        }else {
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if (StringUtils.isNullOrEmpty(queryProviderId)){
            bill.setProviderId(0);
        }else {
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }

        billList = billService.getBillList(bill);
        // 给前端传值
        req.setAttribute("billList",billList);
        req.setAttribute("queryProductName",queryProductName);
        req.setAttribute("queryProviderId",queryProviderId);
        req.setAttribute("queryIsPayment",queryIsPayment);
        // 返回前端页面
        req.getRequestDispatcher("billlist.jsp").forward(req,resp);
    }



}
