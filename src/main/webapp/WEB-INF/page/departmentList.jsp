<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/18
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门操作</title>
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
<form id="depList" class="layui-form">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>部门名</th>
            <th>领导名</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.department}" var="dep">
            <tr>
                <td><input type="checkbox" name="ids" value="${dep.department.id}"></td>
                <td>${dep.department.name}</td>
                <td>${dep.leader.name}</td>
                <td><fmt:formatDate value="${dep.department.createTime}" type="both"/></td>
                <td>
                    <button class="layui-btn layui-btn-normal" type="button" onclick="update('${dep.department.id}')">
                        修改
                    </button>
                    <a href="${pageContext.request.contextPath}/admin/posList?depId=${dep.department.id}">
                        <button class="layui-btn layui-btn-normal" type="button">
                            查看职位
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/admin/showEmp?type=1&id=${dep.department.id}"><input
                            id='showEmpByDep' type='button' class='layui-btn layui-btn-normal' value='查看员工'></a>
                    <a href="${pageContext.request.contextPath}/admin/showTrain?type=1&id=${dep.department.id}"><input
                            id='showTrainByDep' type='button' class='layui-btn layui-btn-normal' value='查看培训'></a>

                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <input id="addDep" type="button" class="layui-btn layui-btn-normal" value="添加部门">
            </td>
            <td>
                <button type="button" id="delete" class="layui-btn">立即删除</button>
            </td>
            <td>><a href="${pageContext.request.contextPath}/admin/showAllEmp"><input type="button" class="layui-btn layui-btn-normal" value="查看全部员工"></a>
                <button type="button" class="layui-btn" onclick="showDeleteEmp()">查看已删除的员工</button>
            </td>
        </tr>
    </table>
</form>
</body>
<form method="post" style="display: none" id="updateDep" class="layui-form">
    <table class="layui-table">
        <input type="hidden" id="depId" name="department.id"/>
        <tr>
            <td>部门名</td>
            <td><input type="text" id="departmentName" name="department.name" required lay-verify="required"
                       placeholder="请输入部门名"
                       autocomplete="off"
                       class="layui-input"></td>
        </tr>
        <tr>
            <td>部门领导</td>
            <td>
                <select name="leader.id" id="leader">

                </select>
            </td>
        </tr>
        <tr>
            <td>
                <button type="button" class="layui-btn-normal layui-btn"  lay-submit lay-filter="update">更新信息</button>
            </td>
        </tr>
    </table>
</form>
<script>
    //Demo
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;

        $("#addDep").click(function () {
            layer.prompt(
                {title: '输入要添加的部门的名字'},
                function (value, index, elem) {
                    if (value != "" && value != null) {
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath}/admin/addDepartment",
                            data: {
                                "department.name": value
                            },
                            success: function (data) {
                                layer.alert(data,function () {
                                    window.setTimeout(location.reload(), 2000);
                                });

                            }
                        });
                    } else {
                        layer.msg("请输入部门名");
                    }
                });
        });

    });
    $("#delete").click(function () {
        var serialize = $("#depList").serialize();

        $.post('${pageContext.request.contextPath}/admin/deleteDep', serialize, function (result) {
            var layer = layui.layer;
            layer.alert(result, function (index) {
                //do something
                window.location.reload();
                layer.close(index);
            });
        });
    });

    function update(id) {
        $.get('${pageContext.request.contextPath}/dep/getDepartment', {"id": id}, function (result) {
            // alert(result.employee);
            $("#departmentName").val(result.depName);
            $("#depId").val(result.depId);
            // alert(result.leader.id);
            if (result.leader != null) {
                $("#leader").append("<option value='" + result.leader.id + "'>" + result.leader.name + "</option>");
            }
            for (var i = 0; i < result.employee.length; i++) {
                var emp = result.employee[i];
                $("#leader").append("<option value='" + emp.id + "'>" + emp.name + "</option>");
            }
            var form = layui.form;
            form.render('select');
            //监听提交

            layer.open({
                area: ['500px', '300px'],
                type: 1,
                content: $('#updateDep')
            });

            form.on('submit(update)', function (data) {
                $.post('${pageContext.request.contextPath}/admin/updateDep', data.field, function (result) {
                var layer = layui.layer;
                layer.alert(result, function (index) {
                    //do something
                    window.location.reload();
                    layer.close(index);
                });
            });
                return false;
            });

        });
    };

    function showDeleteEmp() {
        var layer = layui.layer;
        layer.open({
            title: "查看已删除的员工",
            type: 2,
            content: "${pageContext.request.contextPath}/admin/showDeleteEmployee",
            area: ['900px', '400px'],
            end:function () {
                window.location.reload();
            }
        });
    }
</script>
</html>
