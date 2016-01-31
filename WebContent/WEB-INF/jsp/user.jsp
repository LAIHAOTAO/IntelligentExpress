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
    <span class="greeting">亲爱的 <span id="name">${name} ${gender}</span> 您好!</span>
    <span class="date"><%@ include file="time.jsp" %></span>
</div>

<div class="container">

    <div class="bar">
        <p><a class="show_finger" id="packageQuery" href="JavaScript:void(0);">按快件号查询</a></p>

        <p><a class="show_finger" id="receiveQuery" href="JavaScript:void(0);">按收件人查询</a></p>

        <p><a class="show_finger" id="sendQuery" href="JavaScript:void(0);">按寄件人查询</a></p>

    </div>
    <div class="content">
        <div class="insideBox" id="queryBox">

            <div id="packageId">快 件 编 号 :
                <select name="packageNo" id="packageNo">
                    <%--<c:forEach var="pacId" items="${pacIdList}">${pacId}</c:forEach>--%>
                </select></div>

            <div id="receivePhone">收件人手机号:
                <input type="text" name="receivePhone" size="11" value="${sessionScope.get('user')}" readonly="readonly"/></div>

            <div id="sendPhone">寄件人手机号:
                <input type="text" name="sendPhone" size="11" value="${sessionScope.get('user')}" readonly="readonly"/></div>

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
    </div>
</div>

<div class="foot">
    <%@ include file="foot.jsp" %>
</div>
</body>
</html>
