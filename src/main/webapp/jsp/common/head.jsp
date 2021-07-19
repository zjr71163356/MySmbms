<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <div id="head">
        <div id="car-img"></div>
        <span>超市订单管理系统</span>
        <span id="time">
				时间：xx-xx-xx
        </span>
        <div class="publicHeaderR">
            下午好！${userSession.userName }, 欢迎你！
            <a id="logout" href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </div>

</header>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2" style="background-color: #536B79; box-shadow: 2px 2px 2px 2px #035384;">功能列表</h2>
        <nav>
            <ul class="list">
                <li ><a href="${pageContext.request.contextPath }/jsp/bill.do?method=query">订单管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/provider.do?method=query">供应商管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/user.do?method=query">用户管理</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
                <li><a href="${pageContext.request.contextPath }/jsp/logout.do">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }" />
    <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>" />
