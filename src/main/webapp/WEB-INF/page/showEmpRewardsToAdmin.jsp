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
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<form id="rewards" class="layui-form">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>日期</th>
            <th>原因</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.rewards}" var="reward" varStatus="vs">
            <tr>
                <td><input type="checkbox" name="ids" value="${reward.id}"></td>
                <td><fmt:formatDate value="${reward.time}" type="date"/></td>
                <td>${reward.content}</td>
                <td>${reward.money}</td>
                <td><input type="button" value="修改" class="layui-btn layui-btn-normal" onclick="updateReward('${reward.id}')"></td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <input type="button" value="添加奖惩" class="layui-btn layui-btn-normal" onclick="addReward()">
            </td>
            <td>
                <input type="button" id="delReward" value="删除所选" class="layui-btn layui-btn-normal">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    layui.use(['layer', 'form'], function () {

    });

    function addReward() {
        var layer = layui.layer;
        layer.open({
            area: ['500px', '400px'],
            type: 2,
            content: '${pageContext.request.contextPath}/admin/addRewardsPage?empId=${requestScope.empId}'
        },function () {
            window.location.reload();
        });
    }

    function updateReward(id){
        var layer = layui.layer;
        layer.open({
            area: ['500px', '400px'],
            type: 2,
            content: '${pageContext.request.contextPath}/admin/updateRewardsPage?id='+id
        },function () {
            window.location.reload();
        });
    }

    $('#delReward').click(function () {
        var serialize = $('#rewards').serialize();
        $.post("${pageContext.request.contextPath}/admin/deleteReward", serialize, function (result) {
            var layer = layui.layer;
            var index = layer.alert(result, function () {
                layer.close(index);
                window.location.reload();
            });
        });
    })
</script>
</html>
