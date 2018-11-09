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
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a></li>
</ul>
<form class="layui-form" id="employees">
    <table class="layui-table">
        <tr>
            <td>选择</td>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>员工状态</th>
            <th>所在部门</th>
            <th>所在职位</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.employees}" var="emp">
            <tr>
                <td><input type="checkbox" name="ids" value="${emp.employee.id}"/></td>
                <td>${emp.employee.id}</td>
                <td>${emp.employee.name}</td>
                <td>${emp.employee.status}</td>
                <td>${emp.department.name}</td>
                <td>${emp.position.name}</td>
                <td>
                        <%--<a href="${pageContext.request.contextPath}/emp/updatePage?id=${emp.employee.id}">
                            <input type="button" value="修改" class="layui-btn layui-btn-normal">
                        </a>--%>
                    <input type="button" value="修改" class="layui-btn layui-btn-normal"
                           onclick="updateEmp('${emp.employee.id}')">
                        <%--<a href="${pageContext.request.contextPath}/admin/addRewardsPage?empId=${emp.employee.id}">
                            <input type="button" value="添加奖惩" class="layui-btn layui-btn-normal">
                        </a>--%>
                    <input type="button" value="添加奖惩" class="layui-btn layui-btn-normal"
                           onclick="addReward('${emp.employee.id}')">
                    <input type="button" value="结算薪资" class="layui-btn layui-btn-normal"
                           onclick="salary('${emp.employee.id}')">
                    <table class="layui-table">
                        <tr>
                            <td><input id="yearMonth${emp.employee.id}" name="date" readonly="readonly"
                                       class="layui-input"
                                       placeholder="选择要查看月份"></td>
                            <td>
                                <input type="button" onclick="showAttendance('${emp.employee.id}')" value="查看考勤"
                                       class="layui-btn layui-btn-normal">
                                <input type="button" onclick="showReward('${emp.employee.id}')" value="查看奖惩"
                                       class="layui-btn layui-btn-normal">
                                <input type="button" onclick="showSalary('${emp.employee.id}')" value="查看薪资"
                                       class="layui-btn layui-btn-normal">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" value="添加员工" class="layui-btn layui-btn-normal" onclick="addEmp()"></td>
            <td><input type="button" value="删除员工" lay-submit lay-filter="delete" class="layui-btn layui-btn-normal"></td>
        </tr>
    </table>
</form>
</body>
<script>
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        form.on('submit(delete)', function(data){

            $.post("${pageContext.request.contextPath}/admin/deleteEmployee", $("#employees").serialize(), function (result) {
                layer.alert(result, function () {
                    window.location.reload();
                })
            });
            return false;
        });
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;


        lay('[name="date"]').each(function () {
            laydate.render({
                elem: this,
                format: 'yyyy年M月',
                type: 'month',
                value: new Date()
            });
        });

        /*laydate.render({
            elem: '[name="date"]',
            format: 'yyyy年M月',
            type: 'month',
            value: new Date()
        });*/
    });

    function updateEmp(empId) {
        var layer = layui.layer;
        layer.open({
            title: "修改员工",
            type: 2,
            content: "${pageContext.request.contextPath}/admin/updatePage?id=" + empId,
            area: ['500px', '400px'],
            end: function () {
                window.location.reload();
            }
        });
    };

    function addReward(empId) {
        var layer = layui.layer;
        layer.open({
            title: "添加奖惩",
            type: 2,
            content: "${pageContext.request.contextPath}/admin/addRewardsPage?empId=" + empId,
            area: ['500px', '400px'],
            end: function () {
                window.location.reload();
            }
        });
    };

    function addEmp() {
        var layer = layui.layer;
        layer.open({
            title: "添加员工",
            type: 2,
            content: "${pageContext.request.contextPath}/admin/addEmployeePage",
            area: ['500px', '400px'],
            end: function () {
                window.location.reload();
            }
        });
    };

    function showAttendance(empId) {
        var date = $("#yearMonth" + empId).val();
        layer.open({
            type: 2,//弹出框类型
            title: '用户考勤记录',
            shadeClose: false, //点击遮罩关闭层
            area: ['60%', '90%'],
            shift: 1,
            content: "${pageContext.request.contextPath}/admin/showAttendance?empId=" + empId + "&date=" + date,
            success: function (layero, index) {
            }
        });
    };

    function showReward(empId) {
        var date = $("#yearMonth" + empId).val();
        layer.open({
            type: 2,//弹出框类型
            title: '用户奖惩记录',
            shadeClose: false, //点击遮罩关闭层
            area: ['60%', '90%'],
            shift: 1,
            content: "${pageContext.request.contextPath}/admin/showRewards?empId=" + empId + "&date=" + date,
            success: function (layero, index) {
            }
        });
    };

    function showSalary(empId) {
        var val = $("#yearMonth" + empId).val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/emp/getSalary",
            data: {"month": val, "empId": empId},
            success: function (data) {
                if (JSON.stringify(data).length != 2) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        var index2 = layer.open({
                            area: ['500px', '300px'],
                            type: 1,
                            content: "<table class='layui-table'><tr><th>基本工资</th><th>绩效工资</th><th>加班工资</th><th>奖惩</th><th>结算时间</th></tr>" +
                                "<tr><td>" + data.basic + "</td><td>" + data.performance + "</td><td>" + data.overtime + "</td><td>" + data.rewards + "</td><td>" + data.time + "</td>" + "</table>",
                        });
                    });
                } else {
                    layui.use('layer', function () {
                        layer.msg('薪资尚未结算');
                    });
                }
            }
        })
    }

    function salary(id) {
        layer.open({
            type: 2,//弹出框类型
            title: '结算薪资',
            shadeClose: false, //点击遮罩关闭层
            area: ['60%', '90%'],
            shift: 1,
            content: "${pageContext.request.contextPath}/admin/computeSalaryPage?empId=" + id,
            success: function (layero, index) {
            }
        });
    }
</script>
</html>
