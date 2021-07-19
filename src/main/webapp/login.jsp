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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body{
            position: relative;
            height: 100vh;
            width: 100%;
            background: #7698AD;
        }
        .loginHeader {
            position: absolute;
            left: 50%;
            top: 5%;
            transform: translate(-50%,0);
            text-align: center;
            height: 100px;
            line-height: 100px;
            width: 400px;
            border-bottom: 3px solid #FFFFFF;
            font-size: 30px;
            color: #FFFFFF;
            font-weight: bold;
        }
        #login-right {
            position: absolute;
            top: 30%;
            left: 58%;
            width: 400px;
            height: 370px;
            border: 5px solid #FFFFFF;
            font-size: 30px;
            color: #FFFFFF;
            font-weight: bold;
            transition: all .5s ease;
        }
        .login-info{
            height: 50px;
            width: 100%;
            line-height: 50px;
            text-align: center;
            font-weight: bold;
            font-size: 25px;
            color: #DD7777;
        }
        #login-right:hover{
            box-shadow: 2px 2px 2px 2px #536B79;
            border-radius: 15px;
        }
        #login-input {
            position: relative;
            width: 100%;
            height: 100%;
        }
        .input-common {
            width: 90%;
            position: absolute;
            left: 50%;
            transform: translate(-50%,0);
        }
        #inputbox-one {
            top: 15%;
        }
        #inputbox-two {
            top: 30%;
        }
        .input-common input {
            width: 70%;
            height: 30px;
            background-color: #7698AD;
            border: none;
            border-bottom: 3px solid #FFFFFF;
            padding: 0;
            outline: none;
            font-size: 25px;
            font-weight: bold;
            -webkit-box-shadow: 0 0 0 1000px #7698AD inset;
            color: #ffffff;
        }
        .subBtn {
            display: flex;
            justify-content: space-around;
            position: absolute;
            left: 50%;
            transform: translate(-50%,0);
            top: 62%;
            width: 80%;
            height: 50px;
        }
        .subBtn input {
            width: 120px;
            height: 40px;
            background-color: rgba(0,0,0,0);
            border: 3px solid #ffffff;
            border-radius: 10px;
            transition: all .5s ease;
            color: #FFFFFF;
        }
        .subBtn input:hover{
            box-shadow: 0 2px 2px 2px #536B79;
            font-size: 20px;
        }
    </style>
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

