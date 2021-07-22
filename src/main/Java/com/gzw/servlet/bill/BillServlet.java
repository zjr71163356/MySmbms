package com.gzw.servlet.bill;
import com.alibaba.fastjson.JSONArray;
import com.gzw.pojo.Bill;
import com.gzw.pojo.Provider;
import com.gzw.pojo.User;
import com.gzw.service.bill.BillService;
import com.gzw.service.bill.BillServiceImpl;
import com.gzw.service.provider.ProviderService;
import com.gzw.service.provider.ProviderServiceImpl;
import com.gzw.util.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doGet(req, resp); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("query")&&method!=null){
            this.query(req,resp);
        }else if (method.equals("add")&&method!=null){
            this.add(req, resp);
        }else if(method != null && method.equals("view")){
            this.getBillById(req,resp,"billview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getBillById(req,resp,"billmodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }else if(method != null && method.equals("delbill")){
            this.delBill(req,resp);
        }else if(method != null && method.equals("getproviderlist")){
            this.getProviderlist(req,resp);
        }
    }

    private void getProviderlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("","");
        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
    private void getBillById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            Bill bill = null;
            bill = billService.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        BigDecimal productCount = new BigDecimal(request.getParameter("productCount")).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice")).setScale(2, BigDecimal.ROUND_DOWN);
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.modify(bill);
        if(flag){
            response.sendRedirect(request.getContextPath()+"/jsp/bill.do?method=query");
        }else{
            request.getRequestDispatcher("billmodify.jsp").forward(request, response);
        }
    }
    private void delBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            boolean flag = billService.deleteBillById(id);
            if(flag){//删除成功
                resultMap.put("delResult", "true");
            }else{//删除失败
                resultMap.put("delResult", "false");
            }
        }else{
            resultMap.put("delResult", "notexit");
        }
        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        BigDecimal productCount = new BigDecimal(req.getParameter("productCount")).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal totalPrice = new BigDecimal(req.getParameter("totalPrice")).setScale(2, BigDecimal.ROUND_DOWN);
        Integer providerId = Integer.parseInt(req.getParameter("providerId"));
        Integer isPayment = Integer.parseInt(req.getParameter("isPayment"));
        String productDesc = req.getParameter("productDesc");
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        bill.setProductDesc(productDesc);

        // 插入bill
        boolean flag = false;
        flag = billService.add(bill);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
        }else {
            req.getRequestDispatcher("billadd.jsp").forward(req, resp);
        }

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
