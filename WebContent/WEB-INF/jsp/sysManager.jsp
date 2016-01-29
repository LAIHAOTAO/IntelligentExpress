<%--
  Created by IntelliJ IDEA.
  User: ERIC_LAI
  Date: 15/11/24
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manager</title>
    <link href="../../css/style.css" type="text/css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>
    <script src="../../js/sysManager.js"></script>
    <script src="../../jquery-ui-1.11.4/jquery-ui.min.js"></script>
</head>
<body>

<div class="head">
    <%@ include file="head.jsp" %>
</div>

<div class="title">
    <h2>管理员后台</h2>
</div>

<div class="info">
    <span class="greeting">亲爱的<span id="name">×××</span>管理员您好!</span>
    <span class="date"><%@ include file="time.jsp"%></span>
</div>

<div class="container">

    <div class="bar">
        <p><a class="show_finger" id="addPostman" href="JavaScript:void(0);">添加邮递员</a></p>

        <p><a class="show_finger" id="managePostman" href="JavaScript:void(0);">管理邮递员</a></p>

        <p><a class="show_finger" id="logout" href="JavaScript:void(0);">注销并登出</a></p>
    </div>

    <div class="content">

        <div id="addBox" class="insideBox">
            <form method="post" action="/sysManager/addPostman">
                <p><span class="star">*</span> 姓&nbsp;&nbsp;&nbsp;&nbsp;名:<input name="name" type="text"/></p>

                <p><span class="star">*</span> 手机号:<input name="phone" type="text"/></p>

                <p><span class="star">*</span> 用户名:<input name="userName" type="text"/></p>

                <p><span class="star">*</span> 密&nbsp;&nbsp;&nbsp;&nbsp;码:<input name="password" type="text"/></p>

                <p><span class="star">*</span> 性&nbsp;&nbsp;别:&nbsp;&nbsp;<input name="gender" type="radio" value="0"/>男
                    &nbsp;&nbsp;&nbsp;&nbsp;<input name="gender" type="radio" value="1"/>女
                </p>

                <p class="center"><input class="right" type="submit" value="添加新邮递员"/></p>
            </form>
        </div>

        <div id="manageBox" class="hide">

        </div>

    </div>


</div>

<div class="foot">
    <%@ include file="foot.jsp"%>
</div>
</body>
</html>
