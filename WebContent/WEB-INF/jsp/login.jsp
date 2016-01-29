<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="../../css/style.css">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="../../js/login.js"></script>
    <title>Login Page</title>
</head>
<body>
<div class="head">
    <%@ include file="head.jsp" %>
</div>
<div class="title">
    <h2>登录</h2>
</div>
<div class="container">

    <form action="/login/loginCheck" method="POST">
        <input type="hidden" name="result" id="result" value="${result}"/>

        <div id="loginBox">
            <p>
                用户名<input type="text" name="userName"/>
            </p>
            <span id="userNameMsg"></span>

            <p>
                密 码<input type="password" name="password"/>
            </p>
            <span id="passwordMsg"></span>

            <div id="selectRole">
                <input type="radio" name="role" value="0"/>管理员
                <input type="radio" name="role" value="1"/>邮递员
                <input type="radio" name="role" value="2"/>寄件/收件人
            </div>
            <br/>
            <input type="submit" value="进入系统"/>

            <div id="mistake"></div>
        </div>
    </form>

</div>
<div class="foot">
    <%@ include file="foot.jsp" %>
</div>
</body>
</html>