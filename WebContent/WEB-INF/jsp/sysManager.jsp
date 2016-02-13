<%--
  Created by IntelliJ IDEA.
  User: ERIC_LAI
  Date: 15/11/24
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>manager</title>
    <link href="../../css/style.css" type="text/css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="../../layer/layer.js"></script>
    <script src="../../js/sysManager.js"></script>
</head>
<body>

<div class="head">
    <%@ include file="head.jsp" %>
</div>

<div class="title">
    <h2>管理员后台</h2>
</div>

<div class="info">
    <span class="greeting">亲爱的 <span id="name">${name}</span> 管理员您好!</span>
    <span class="date"><%@ include file="time.jsp" %></span>
</div>

<div class="container">

    <div class="bar">
        <p><a class="show_finger" id="addPostman" href="JavaScript:void(0);">添加邮递员</a></p>
        <p><a class="show_finger" id="managePostman" href="JavaScript:void(0);">管理邮递员</a></p>
    </div>

    <div class="content">
        <div id="addBox" class="insideBox">
            <p><span class="star">*</span> 姓&nbsp;名:<input id="posterNm" name="name" type="text" class="right"/></p>
            <p><span class="star">*</span> 手机号:<input id="posterPh" name="phone" type="text" class="right"/></p>
            <p><span class="star">*</span> 登录名:<input id="posterLogNm" name="userName" type="text" class="right"/></p>
            <p><span class="star">*</span> 密&nbsp;码:<input id="posterLogPw" name="password" type="text" class="right"/></p>
            <p><span class="star">*</span> 性&nbsp;别:&nbsp;<span id="rightRadio">
                <input name="gender" type="radio" value="0"/>男&nbsp;&nbsp;
                <input name="gender" type="radio" value="1"/>女</span>
            </p>
            <p class="center"><input type="submit" value="添加新邮递员"/></p>
        </div>

        <div id="manageBox" class="hide">

        </div>

    </div>


</div>

<div class="foot">
    <%@ include file="foot.jsp" %>
</div>
</body>
</html>
