<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/19
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<head>
    <title>计算薪资</title>
</head>
<body>
<form id="salary" class="layui-form" method="post" action="${pageContext.request.contextPath}/admin/computeSalary">
    <table class="layui-table">
        <input type="hidden" name="empId" value="${requestScope.empId}"/>
        <tr>
            <td>结算月份</td>
            <td><input id="date" class="layui-input" autocomplete="off" name="date"></td>
        </tr>
        <tr>
            <td>绩效工资</td>
            <td><input id="performance" class="layui-input" autocomplete="off" name="performance"></td>
        </tr>
        <tr><td><button type="submit" class="layui-btn layui-btn-normal">确认</button> </td></tr>
    </table>
</form>
</body>
<script>
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            type:'month',
            elem: '#date' ,
            format:'yyyy年MM月'
        });
    });
</script>
</html>
