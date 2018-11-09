<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<head>
    <title>查看培训</title>
</head>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a></li>
</ul>
<form class="layui-form" id="trains">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>培训信息</th>
            <th>培训时间</th>
            <th>所在部门</th>
            <th>所在职位</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.trains}" var="train">
            <tr>
                <td><input type="checkbox" name="ids" value="${train.train.id}"/></td>
                <td>${train.train.content}</td>
                <td><fmt:formatDate value="${train.train.time}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></td>
                <td>${train.department.name}</td>
                <td>${train.position.name}</td>
                <td><input type="button" class="layui-btn layui-btn-normal" value="更改培训"
                           onclick="updateTrain('${train.train.id}','${train.train.content}','<fmt:formatDate
                                   value="${train.train.time}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/>')"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" class="layui-btn layui-btn-normal" value="添加培训" onclick="addTrain()"/></td>
            <td><input type="button" id="delete" class="layui-btn layui-btn-normal" value="删除培训"
                       lay-submit lay-filter="delete"/></td>
        </tr>
    </table>
</form>
</body>

<form style="display: none" class="layui-form" id="train">
    <table class="layui-table">
        <input type="hidden" name="train.id" id="trainId"/>
        <tr>
            <td>培训详情</td>
            <td><input id="content" name="train.content" type="text" required lay-verify="required"
                       placeholder="请输入培训详情" autocomplete="off"
                       class="layui-input"></td>
        </tr>
        <tr>
            <td>培训时间</td>
            <td><input name="train.time" id="date" readonly required lay-verify="required" placeholder="请选择培训日期"
                       autocomplete="off"
                       class="layui-input"></td>
        </tr>
        <tr>
            <td>针对部门</td>
            <td>
                <select name="department.id" class="layui-form-select" lay-filter="department" required
                        lay-verify="required">
                    <c:forEach items="${requestScope.department}" var="dep">
                        <option value="${dep.department.id}">${dep.department.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>针对职位</td>
            <td>
                <select name="position.id" id="position" lay-filter="position" class="layui-form-select" required
                        lay-verify="required">
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="submit" value="确认"
                       id="submit"/></td>
        </tr>
    </table>
</form>

<script>
    layui.use(['layer', 'form', 'laydate'], function () {

        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;

        form.on('submit(delete)', function (data) {
            $.post("${pageContext.request.contextPath}/admin/deleteTrain", $("#trains").serialize(), function (result) {
                layer.alert(result, function () {
                    window.location.reload();
                })
            });
            return false;
        });

        laydate.render({
            elem: '#date',
            type: 'datetime',
            value: new Date(),
            format: 'yyyy年MM月dd日 HH时mm分ss秒'

        });

        form.on('select(department)', function (data) {
            if (data.value.length < 5) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dep/getPositions",
                    method: "POST",
                    data: {"id": data.value},
                    success: function (parse) {
                        document.getElementById("position").length = 0;
                        for (var i = 0; i < parse.length; i++) {
                            var pos = parse[i];
                            $("#position").append("<option value=" + pos.id + ">" + pos.name + "</option>");
                        }
                        form.render();
                    }
                });

            }
        });
    });

    function addTrain() {
        $("#trainId").val("");
        $("#content").val("");


        var layer = layui.layer;
        layer.open({
            type: 1,//弹出框类型
            title: '添加培训',
            shadeClose: false, //点击遮罩关闭层
            area: ['60%', '50%'],
            shift: 1,
            content: $("#train"),
            success: function (layero, index) {
            }
        });

        var form = layui.form;

        //监听提交
        form.on('submit(submit)', function (data) {

            if (data.field['position.id'] != "") {
                $.post("${pageContext.request.contextPath}/admin/addTrain", data.field, function (result) {
                    layer.alert(result, function () {
                        window.location.reload();
                    })
                });
                return false;
            }else {
                layer.msg("请选择职位");
            }
        });
    }

    function updateTrain(id, content, time) {
        $("#trainId").val(id);
        $("#content").val(content);
        $("#date").val(time);
        var layer = layui.layer;
        layer.open({
            type: 1,//弹出框类型
            title: '添加培训',
            shadeClose: false, //点击遮罩关闭层
            area: ['60%', '50%'],
            shift: 1,
            content: $("#train"),
            success: function (layero, index) {
            }
        });

        var form = layui.form;

        //监听提交
        form.on('submit(submit)', function (data) {

            if (data.field['position.id']!=null) {
                $.post("${pageContext.request.contextPath}/admin/updateTrain", data.field, function (result) {
                    layer.alert(result, function () {
                        window.location.reload();
                    })
                });
                return false;
            }else {
                layer.msg("请选择职位");
            }
        });

    }
</script>

</html>
