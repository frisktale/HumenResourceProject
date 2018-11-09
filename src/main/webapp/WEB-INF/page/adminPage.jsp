<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/8
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员页面</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a></li>
</ul>
<table class="layui-table">
    <tr>
        <th><span class="layui-text">待处理异议薪资</span></th>
    </tr>
    <tr>
        <th>基本工资</th>
        <th>绩效工资</th>
        <th>加班工资</th>
        <th>奖惩</th>
        <th>结算时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.objection}" var="obj">
        <tr>
            <td>${obj.salary.basic}</td>
            <td>${obj.salary.performance}</td>
            <td>${obj.salary.overtime}</td>
            <td>${obj.salary.rewards}</td>
            <td><fmt:formatDate value="${obj.salary.time}" type="both"/> </td>
            <td>
                <button class="layui-btn" onclick="showEmp('${obj.salary.id}')">查看对应员工</button>

            </td>
        </tr>
        <tr>
            <td colspan="6" class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:;">查看详情</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:proc('${obj.salary.id}','${obj.objectionSalary.id}','${obj.objectionSalary.money}');">错误信息:${obj.objectionSalary.content}
                                错误金额:${obj.objectionSalary.money}</a></dd>
                    </dl>
                </li>
            </td>
        </tr>
    </c:forEach>
</table>

</body>


</html>
<script>
    layui.use(['element', 'layer', 'form'], function () {
        var element = layui.element;
        var layer = layui.layer;

    });

    function showEmp(id) {
        var layer = layui.layer;
        var index = layer.open({
            type: 2,
            area: ['1000px', '500px'],
            content: '${pageContext.request.contextPath}/admin/showEmpBySalary?id=' + id
        });
    }

    function proc(salaryId, objId, money) {
        var layer = layui.layer;
        var index = layer.confirm('是否补发工资？', {
            btn: ['是', '否']
        }, function (index, layero) {
            // Integer salaryId, Integer objId, Double money,Boolean isExecute
            $.post('${pageContext.request.contextPath}/admin/processObjectionSalary', {
                "salaryId": salaryId,
                "objId": objId,
                "money": money,
                "isExecute": true
            }, function (data) {
                layer.alert(data,function () {
                    window.location.reload();
                });
            });
            layer.close(index);
            <%--window.location.href = "${pageContext.request.contextPath}/admin/adminCenter";--%>
        }, function (index) {
            $.post('${pageContext.request.contextPath}/admin/processObjectionSalary', {
                "salaryId": salaryId,
                "objId": objId,
                "money": 0,
                "isExecute": false
            }, function (data) {
                layer.alert(data,function () {
                    window.location.reload();
                });
            });
            layer.close(index);
            <%--window.location.href = "${pageContext.request.contextPath}/admin/adminCenter";--%>
        });
    }
</script>