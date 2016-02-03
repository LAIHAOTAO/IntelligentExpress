<%--
  Created by IntelliJ IDEA.
  User: ERIC_LAI
  Date: 15/11/24
  Time: 下午9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>user</title>
    <link href="../../css/style.css" type="text/css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="../../layer/layer.js"></script>
    <script src="../../js/user.js"></script>
</head>
<body>
<div class="head">
    <%@ include file="head.jsp" %>
</div>

<div class="title">
    <h2>快件状态查询</h2>
</div>

<div class="info">
    <span class="greeting">亲爱的 <span id="name">${name}</span><span id="gender">${gender}</span> 您好!</span>
    <span class="date"><%@ include file="time.jsp" %></span>
</div>

<div class="container">
    <div class="bar">
        <p><a class="show_finger" id="packageQuery" href="JavaScript:void(0);">按快件号查询</a></p>
        <p><a class="show_finger" id="receiveQuery" href="JavaScript:void(0);">按收件人查询</a></p>
        <p><a class="show_finger" id="sendQuery" href="JavaScript:void(0);">按寄件人查询</a></p>
        <p><a class="show_finger" id="modifyInfo" href="JavaScript:void(0);">修改个人信息</a></p>
        <p><a class="show_finger" id="modifyLogPw" href="JavaScript:void(0);">修改登录密码</a></p>
        <p><a class="show_finger" id="addressManage" href="JavaScript:void(0);">个人地址管理</a></p>
    </div>
    <div class="content">
        <div class="insideBox" id="queryBox">

            <div id="packageId" class="query">快件编号:
                <select name="packageNo" id="packageNo" style="width:150px">
                </select></div>
            <div id="receivePhone" class="query">收件人手机号:
                <input type="text" name="receivePhone" size="11" value="${phone}" readonly="readonly"/></div>
            <div id="sendPhone" class="query">寄件人手机号:
                <input type="text" name="sendPhone" size="11" value="${phone}" readonly="readonly"/></div>
            <div class="center"><br/><input id="queryButton" type="submit" value="开始查询"/></div>

            <div class="resultTableBox" id="queryResult">
                <p id="resultMsg" class="center"></p>
                <table class="table-normal">
                    <thead>
                    <tr>
                        <th>快递编号</th>
                        <th>寄件人</th>
                        <th>寄件人手机</th>
                        <th>收件人</th>
                        <th>收件人手机</th>
                        <th>邮递员</th>
                        <th>邮递员手机</th>
                        <th>快件状态</th>
                    </tr>
                    </thead>
                    <tbody id="record">
                    </tbody>
                </table>
            </div>
        </div>
        <div id="modifyBox" class="insideBox">
            <P>身份ID：<input type="text" id="personId" size="14" class="right" readonly="readonly" value="${personId}"/></P>
            <p>姓名： <input type="text" id="personNm" size="14" class="right" /></p>
            <p>手机号码：<input type="text" id="phone" size="14" class="right" /></p>
            <p>登录名：<input type="text" id="logNm" size="14" class="right" /></p>
            <p>性别：
                <span id="rightRadio">
                    <input type="radio" name="gender"  class="gender" id="male" value="0" />男
                    <input type="radio" name="gender" class="gender" id="female" value="1"/>女
                </span>
            </p>
            <p class="center"><input type="submit" id="sureModify" value="修改信息" /></p>
        </div>
        <div id="modifyPw" class="insideBox">
            <p>旧密码：<input type="password" id="oldPw" class="right" size="14" /></p>
            <p>新密码：<input type="password" id="newPw" class="right" size="14" /></p>
            <p>再次新密码：<input type="password" id="againNewPw" class="right" size="14" /></p>
            <p class="center"><input type="submit" id="changePw" value="修改密码" /></p>
        </div>
        <div id="addressBox" class="insideBox">
            <table class="table-normal" width="100%">
                <thead>
                <tr>
                    <th width="30%">地址编号</th>
                    <th width="50%">详细地址</th>
                    <th width="20%">操作</th>
                </tr>
                </thead>
                <tbody id="addressRecord">
                </tbody>
            </table>
            <br/>
            <p class="center"><input type="submit" value="增加新的地址" /></p>
        </div>
    </div>
</div>

<div class="foot">
    <%@ include file="foot.jsp" %>
</div>
</body>
</html>
