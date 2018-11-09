<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/18
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>职位操作</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a></li>
</ul>
<form id="posList" class="layui-form">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>职位名</th>
            <th>部门名</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.department.positions}" var="pos">
            <tr>
                <td><input type="checkbox" name="ids" value="${pos.id}"></td>
                <td>${pos.name}</td>
                <td>${requestScope.department.department.name}</td>
                <td><fmt:formatDate value="${pos.createTime}" type="both"/></td>
                <td>
                    <button class="layui-btn layui-btn-normal" type="button" onclick="update('${pos.id}')">
                        修改
                    </button>
                    <a href='${pageContext.request.contextPath}/admin/showEmp?type=0&id=${pos.id}'><input
                            type='button' class='layui-btn layui-btn-normal' value='查看员工'></a>
                    <a href='${pageContext.request.contextPath}/admin/showTrain?type=0&id=${pos.id}'><input
                            type='button' class='layui-btn layui-btn-normal' value='查看培训'></a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input id="addPos" type="button" class="layui-btn layui-btn-normal" value="添加职位"></td>
            <td>
                <button type="button" id="delete" class="layui-btn">立即删除</button>
            </td>
        </tr>
    </table>
</form>
</body>

<form id="addPosition" class="layui-form" style="display:none;">
    <table class="layui-table">
        <tr>
            <td>输入职位名称</td>
            <td><input type="text" name="posName" class="layui-input"></td>
        </tr>
        <tr>
            <td>选择职位所属部门</td>
            <td>
                <select name="depId" class="layui-form-select">
                    <option>请选择部门</option>
                    <c:forEach items="${requestScope.allDepartment}" var="dep">
                        <option value="${dep.department.id}">${dep.department.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input class="layui-btn"  lay-submit lay-filter="formDemo" type="button" id="getmsg" value="添加"></td>
        </tr>
    </table>

</form>

<script>

    layui.use(['element', 'layer', 'form'], function () {
        var element = layui.element;
        var layer = layui.layer;

    });

    $("#getmsg").click(function () {

        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/admin/addPosition",
                data: data.field,
                success: function (data) {
                    layer.msg(data);
                }
            });
            return false;
        });
    });



    $("#addPos").click(function () {
        var layer = layui.layer;
        var index = layer.open({
            type: 1,
            content: $('#addPosition')
        });
        $("#getmsg").click(function () {
            layer.close(index);
        });

    });

    function update(posId) {
        $.post("${pageContext.request.contextPath}/dep/getPosition", {"id": posId}, function (result) {
            var layer = layui.layer;
            layer.prompt({
                formType: 0,
                value: result.name,
                title: '请输入新的职位名'
            }, function (value, index, elem) {
                $.post("${pageContext.request.contextPath}/admin/updatePos", {
                    "position.id": result.id,
                    "position.name": value
                }, function (result1) {
                    layer.alert(result1, function (index) {
                        window.location.reload();
                    });
                });
                layer.close(index);
            });
        })
    };

    $("#delete").click(function () {
        var serialize = $("#posList").serialize();

        $.post('${pageContext.request.contextPath}/admin/deletePos', serialize, function (result) {
            var layer = layui.layer;
            layer.alert(result, function (index) {
                //do something
                window.location.reload();
                layer.close(index);
            });
        });
    });

</script>
</html>
