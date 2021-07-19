<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>

	<style type="text/css">
		.page-bar {
			position: relative;
			width: 100%;
			height: 50px;
			background-color: #536B79;
			border-bottom: 2px solid #FFF3F8;
		}
		.page-bar ul li{
			position: absolute;
			left: 10px;
			top: 50%;
			transform: translate(0,-50%);
			color: #FFF3F8;
		}
		/*.page-bar ul a {*/
		/*	position: absolute;*/
		/*	top: 50%;*/
		/*	transform: translate(0,-50%);*/
		/*	display: inline-block;*/
		/*	width: 100px;*/
		/*	height: 20px;*/
		/*	background-color: rgba(0,0,0,0);*/
		/*	border: 2px solid #FFF3F8;*/
		/*	color: #FFF3F8;*/
		/*	text-align: center;*/
		/*}*/
		/*.page-bar .ul-left {*/
		/*	left: 10%;*/
		/*}*/
		/*.page-bar .ul-right{*/
		/*	left: 20%;*/
		/*}*/
		.page-bar #ul-a {
			position: absolute;
			left: 10%;
			top: 50%;
			transform: translate(0,-50%);
		}
		.page-bar a{
			font-size: 15px;
			color: #FFF3F8;
			padding-left: 10px;
		}
		.page-go-form{
			position: absolute;
			top: 50%;
			left: 76%;
			transform: translate(0,-50%);
			color: #FFF3F8;
			font-size: 15px;
		}
		.page-go-form input{
			height: 20px;
			width: 100px;
			border-radius: 5px;
			background-color: rgba(0,0,0,0);
			border: 2px solid #FFF3F8;
			color: #FFF3F8;
			font-size: 20px;
		}
		.page-go-form button {
			width: 80px;
			height: 25px;
			margin-left: 20px;
			background-color: rgba(0,0,0,0);
			border-radius: 5px;
			border: 2px solid #FFF3F8;
			color: #FFF3F8;
			font-size: 15px;
		}
		.page-go-form button:hover{
			font-size: 18px;
		}
		.page-go-form button:active{
			background-color: #FFFFFF;
		}
	</style>
</head>
<body>
 		<div class="page-bar">
			<ul class="page-num-ul clearfix">
				<li>共${param.totalCount }条记录&nbsp;&nbsp; ${param.currentPageNo }/${param.totalPageCount }页</li>
				<span id="ul-a">
					<c:if test="${param.currentPageNo > 1}">
						<a class="ul-left" href="javascript:page_nav(document.forms[0],1);">首页</a>
						<a class="ul-left" href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
					</c:if>
					<c:if test="${param.currentPageNo < param.totalPageCount }">
						<a class="ul-right" href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
						<a class="ul-right" href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a>
					</c:if>
				</span>&nbsp;&nbsp;
			</ul>
		 <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
		</div> 
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rollpage.js"></script>
</html>