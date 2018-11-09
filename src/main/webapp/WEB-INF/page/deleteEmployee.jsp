<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/22
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已删除的员工</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<form class="layui-form" id="employees">
    <table class="layui-table">
        <tr>
            <td>选择</td>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工状态</th>
            <th>所在部门</th>
            <th>所在职位</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td><input type="checkbox" name="ids" value="${emp.employee.id}"/></td>
                <td>${emp.employee.id}</td>
                <td>${emp.employee.name}</td>
                <td>${emp.employee.status}</td>
                <td>${emp.department.name}</td>
                <td>${emp.position.name}</td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" value="恢复员工" lay-submit lay-filter="recover" class="layui-btn layui-btn-normal"></td>
            <td><a href="${pageContext.request.contextPath}/admin/cleanEmployee"><input type="button" value="清除已删除员工"
                                                                                        class="layui-btn layui-btn-normal"></a>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        form.on('submit(recover)', function (data) {

            $.post("${pageContext.request.contextPath}/admin/recoverEmployee", $("#employees").serialize(), function (result) {
                layer.alert(result, function () {
                    window.location.reload();
                })
            });
            return false;
        });
    });

</script>
</html>
