<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/12
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤记录</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<body>
<table class="layui-table">
    <tr>
        <th>考勤日期</th>
        <th>上班打卡时间</th>
        <th>下班打卡时间</th>
        <th>状态</th>
        <th>是否迟到</th>
        <th>是否旷工</th>
    </tr
    <c:forEach items="${requestScope.attendances}" var="atte">
        <tr>
            <td><fmt:formatDate value="${atte.startWorkTime}" type="date"/></td>
            <td><fmt:formatDate value="${atte.startWorkTime}" type="time"/></td>
            <td><fmt:formatDate value="${atte.offWorkTime}" type="time"/></td>
            <td>${atte.status}</td>
            <td>${atte.isLate}</td>
            <td>${atte.isAbsent}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
