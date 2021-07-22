<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <script type="text/javascript">
        /* if(top.location!=self.location){
              top.location=self.location;
         } */
    </script>
    <link type="text/css" rel="stylesheet" href="css/login.css" />
</head>
<body class="login_bg">
<section class="loginBox">
    <header class="loginHeader">
        <h1>超市订单管理系统</h1>
    </header>
    <section class="loginCont">
        <form class="loginForm" action="${pageContext.request.contextPath }/login.do"  name="actionForm" id="actionForm"  method="post" >
            <div id="login-left"></div>
            <div id="login-right">
                <div class="login-info">${error}</div>
                <div id="login-text" class="login-info" >
                    登录
                </div>
                <div id="login-input">
                    <div class="input-common" id="inputbox-one">
                        <label for="userCode">用户名:</label>
                        <input type="text" class="input-text" id="userCode" name="userCode" required/>
                    </div>
                    <div class="input-common" id="inputbox-two">
                        <label for="userPassword">密&nbsp;&nbsp;&nbsp;码:</label>
                        <input type="password" id="userPassword" name="userPassword" required/>
                    </div>
                    <div class="subBtn">
                        <input type="submit" value="登录"/>
                        <input type="reset" value="重置"/>
                    </div>
                </div>
            </div>
        </form>
    </section>
</section>
</body>
</html>


