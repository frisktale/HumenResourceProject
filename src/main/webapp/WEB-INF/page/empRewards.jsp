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
    <title>奖惩记录</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<body>
<table class="layui-table">
    <tr>
        <th>日期</th>
        <th>原因</th>
        <th>金额</th>
    </tr>
    <c:forEach items="${requestScope.rewards}" var="reward">
        <tr>
            <td><fmt:formatDate value="${reward.time}" type="date"/></td>
            <td>${reward.content}</td>
            <td>${reward.money}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
