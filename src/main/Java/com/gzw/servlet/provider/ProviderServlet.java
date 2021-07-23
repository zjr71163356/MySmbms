package com.gzw.servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.gzw.pojo.Provider;
import com.gzw.pojo.User;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req,resp);
        }else if(method != null && method.equals("add")){
            this.add(req,resp);
        }else if(method != null && method.equals("view")){
            this.getProviderById(req,resp,"providerview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getProviderById(req,resp,"providermodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }else if(method != null && method.equals("delprovider")){
            this.delProvider(req,resp);
        }

    }


    private void delProvider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("proid");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(id)){
            ProviderService providerService = new ProviderServiceImpl();
            int flag = providerService.deleteProviderById(id);
            if(flag == 0){//删除成功
                resultMap.put("delResult", "true");
            }else if(flag == -1){//删除失败
                resultMap.put("delResult", "false");
            }else if(flag > 0){//该供应商下有订单，不能删除，返回订单数
                resultMap.put("delResult", String.valueOf(flag));
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

    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proAddress = request.getParameter("proAddress");
        String proFax = request.getParameter("proFax");
        String proDesc = request.getParameter("proDesc");
        String id = request.getParameter("id");
        Provider provider = new Provider();
        provider.setId(Integer.valueOf(id));
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDesc(proDesc);
        provider.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        boolean flag = false;
        ProviderService providerService = new ProviderServiceImpl();
        flag = providerService.modify(provider);
        if(flag){
            response.sendRedirect(request.getContextPath()+"/jsp/provider.do?method=query");
        }else{
            request.getRequestDispatcher("providermodify.jsp").forward(request, response);
        }
    }

    private void getProviderById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("proid");
        if(!StringUtils.isNullOrEmpty(id)){
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = null;
            provider = providerService.getProviderById(id);
            request.setAttribute("provider", provider);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws  ServletException ,IOException{
        Object[] paras = req.getParameterValues("paras");
//        System.out.println(Arrays.toString(paras));

        Provider provider = new Provider();
        provider.setProvider(paras,req,resp);
        ProviderServiceImpl providerService = new ProviderServiceImpl();

//        System.out.println(provider.toString());
        boolean flag=providerService.add(provider);
        if(flag==true) {
            req.setAttribute("message", "添加成功！");
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        }
        else  {req.setAttribute("message","添加失败请重试");
        req.getRequestDispatcher("provideradd.jsp").forward(req,resp);}
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws  ServletException ,IOException
    {
        String proid = req.getParameter("proid");
        HashMap<String,String > resultMap=new HashMap<String, String>();
        if(!StringUtils.isNullOrEmpty(proid)) {
            ProviderServiceImpl providerService = new ProviderServiceImpl();
        int flag=providerService.deleteProviderById(proid);
        if(flag==0)
        {
            resultMap.put("delResult","true");
        }
        else
        {
            resultMap.put("delResult",Integer.toString(flag));
        }

        }
        else
        {
            resultMap.put("delResult", "notexit");
        }

            resp.setContentType("application/json");
            PrintWriter out=resp.getWriter();
            out.write(JSONArray.toJSONString(resultMap));
            out.flush();
            out.close();



    }

    private  List<Provider> query(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        String pageIndexinfo = req.getParameter("pageIndex");

        List<Provider> providerList;
        // 英文一个queryCode或queryName只能确定一个供应商
        // 有且只能查到一个供应商
        int currentPageNo = 1;
        //全部页面只有第一页
        int totalPageCount=1;
        //查询结果只能为1
        int totalCount = 1;
        if(pageIndexinfo!=null)
            currentPageNo=Integer.parseInt(pageIndexinfo);
        if(queryProCode==null)
            queryProCode="";
        if(queryProName==null)
            queryProCode="";
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        providerList=providerService.getProviderList(queryProName,queryProCode);

        req.setAttribute("providerList",providerList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
        return  providerList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
