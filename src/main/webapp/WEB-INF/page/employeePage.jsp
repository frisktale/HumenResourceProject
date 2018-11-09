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
    <title>员工页面</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/emp/employeeCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/dep/showDepartment">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/emp/showEmp">查看所在部门的其他职员</a></li>
</ul>



<c:choose>
    <c:when test="${empty requestScope.attendance}">
        <a href="${pageContext.request.contextPath}/emp/startCheck">
            <button class="layui-btn-normal layui-btn">上班打卡</button>
        </a>
    </c:when>
    <c:when test="${not empty requestScope.attendance.offWorkTime}">
        上班签到时间:<fmt:formatDate value="${requestScope.attendance.startWorkTime}" type="both"/> <br/>
        下班签到时间:<fmt:formatDate value="${requestScope.attendance.offWorkTime}" type="both"/><br/>
    </c:when>
    <c:otherwise>
        上班签到时间:<fmt:formatDate value="${requestScope.attendance.startWorkTime}" type="both"/>
        <a href="${pageContext.request.contextPath}/emp/offCheck"><br/>
            <button class="layui-btn-normal layui-btn">下班打卡</button>
        </a>
    </c:otherwise>
</c:choose>

<hr class="layui-bg-blue">
<button class="layui-btn layui-btn-normal" onclick="updatePass()">修改密码</button>

<table class="layui-table">
    <tr>
        <td><input id="salaryMonth" name="date" readonly="readonly" class="layui-input" placeholder="选择要查看的月份"></td>
        <td><input id="getSalary" type="button" value="查看工资" class="layui-btn layui-btn-normal"></td>
    </tr>
</table>
<br/>
<table class="layui-table">
    <thead>培训</thead>
    <tr>
        <th>培训内容</th>
        <th>培训时间</th>
        <th>部门</th>
        <th>职位</th>
    </tr>
    <c:forEach items="${requestScope.trains}" var="train">
        <tr>
            <td>${train.train.content}</td>
            <td><fmt:formatDate value="${train.train.time}" type="both"/></td>
            <td>${train.department.name}</td>
            <td>${train.position.name}</td>
        </tr>
    </c:forEach>

</table>
<table class="layui-table">
    <form method="post" class="layui-form" action="${pageContext.request.contextPath}/emp/showAttendance">
        <tr>
            <td>查看该月考勤状况</td>
            <td><input name="date" readonly="readonly" class="layui-input" placeholder="选择要查看的考勤月份"></td>
            <td><input type="submit" value="确认" class="layui-btn layui-btn-normal"></td>
        </tr>
    </form>
    <form method="post" class="layui-form" action="${pageContext.request.contextPath}/emp/showRewards">
        <tr>
            <td>查看该月奖惩状况</td>
            <td><input name="date" readonly="readonly" class="layui-input" placeholder="选择要查看的奖惩月份"></td>
            <td><input type="submit" value="确认" class="layui-btn layui-btn-normal"></td>
        </tr>
    </form>
</table>
</body>

<form id="updatePass" class="layui-form" style="display: none">
    <table class="layui-table">
        <tr>
            <td>输入旧密码</td>
            <td><input type="password" required lay-verify="required" class="layui-input" id="oldPassword"
                       name="oldPassword"></td>
        </tr>
        <tr>
            <td>输入新密码</td>
            <td><input type="password" required lay-verify="required" class="layui-input" id="newPassword"
                       name="newPassword"></td>
        </tr>
        <tr>
            <td>再次输入新密码</td>
            <td><input type="password" required lay-verify="required" class="layui-input" id="reNewPassword"
                       name="reNewPassword"></td>
        </tr>
        <tr>
            <td>
                <button type="button" lay-submit lay-filter="updatePass" class="layui-btn layui-btn-normal">提交</button>
            </td>
        </tr>
    </table>
</form>

<form id="objection" class="layui-form" style="display:none;">
    <table class="layui-table">
        <input type="hidden" name="salary.id" id="salaryId">
        <tr>
            <td>出错内容</td>
            <td><input class="layui-input" name="objectionSalary.content" required
                       lay-verify="required"
                       autocomplete="off"></td>
        </tr>
        <tr>
            <td>金额</td>
            <td><input class="layui-input" name="objectionSalary.money" required
                       lay-verify="required|number"
                       autocomplete="off"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
            </td>
        </tr>
    </table>
</form>

<script>
    layui.use('form', function () {
        var form = layui.form;

        form.on('submit(updatePass)', function (data) {

            $.post("${pageContext.request.contextPath}/user/updatePass", data.field, function (result) {
                layer.alert(result, function () {
                    window.location.reload();
                });
            });
            // layer.msg(JSON.stringify(data.field));
            return false;
        });

        //监听提交
        /*form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });*/
    });

    function updatePass() {
        var layer = layui.layer;
        layer.open({
            title: '修改密码',
            type: 1,
            content: $("#updatePass")
        })
    }

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
    });

    $("#getSalary").click(function () {
        var val = $("#salaryMonth").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/emp/getSalary",
            data: {"month": val},
            success: function (data) {
                if (JSON.stringify(data).length != 2) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        var index2 = layer.open({
                            area: ['500px', '300px'],
                            type: 1,
                            content: "<table class='layui-table'><tr><th>基本工资</th><th>绩效工资</th><th>加班工资</th><th>奖惩</th><th>结算时间</th></tr>" +
                                "<tr><td>" + data.basic + "</td><td>" + data.performance + "</td><td>" + data.overtime + "</td><td>" + data.rewards + "</td><td>" + data.time + "</td>" + "</table>"
                            , btn: ['薪资有问题'],
                            btn1: function (index, layero) {
                                $('#salaryId').val(data.id);
                                var index1 = layer.open({
                                    area: ['500px', '300px'],
                                    type: 1,
                                    content: $('#objection')
                                });
                                var form = layui.form;
                                //监听提交
                                form.on('submit(formDemo)', function (data) {
                                    $.ajax({
                                        type: "POST",
                                        url: "${pageContext.request.contextPath}/emp/objectionSalary",
                                        data: data.field,
                                        success: function (result) {
                                            layer.alert(result);
                                            layer.close(index1);
                                            layer.close(index2);
                                        }
                                    });
                                    return false;
                                });
                            }
                        });
                    });

                } else {
                    layui.use('layer', function () {
                        layer.msg('薪资尚未结算');
                    });
                }

            }
        })
    })
</script>
</html>
