<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看员工</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/emp/employeeCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/dep/showDepartment">查看部门</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/emp/showEmp">查看所在部门的其他职员</a></li>
</ul>
<form class="layui-form" id="employees">
    <table class="layui-table">
        <tr>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工状态</th>
            <th>所在部门</th>
            <th>所在职位</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td>${emp.employee.id}</td>
                <td>${emp.employee.name}</td>
                <td>${emp.employee.status}</td>
                <td>${emp.department.name}</td>
                <td>${emp.position.name}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
