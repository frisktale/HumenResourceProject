<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/8
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>游客界面</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>

<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/user/userCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/showOffers">查看招聘信息</a></li>
</ul>

<table class="layui-table">
    <tr>
        <td>
            <button class="layui-btn layui-btn-normal" onclick="updatePass()">修改密码</button>
        </td>
        <td>
            <button class="layui-btn layui-btn-normal" onclick="attest()">实名认证</button>
        </td>
    </tr>
</table>

<table class="layui-table">
    <tr>
        <th>面试详情</th>
        <th>面试时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <tr>
    </tr>
    <c:forEach items="${requestScope.interviews}" var="inte">
        <tr>
            <td>${inte.offers.message}</td>
            <td><fmt:formatDate value="${inte.interview.interviewTime}" type="both"/></td>
            <td>${inte.interview.status}</td>
            <td>
                <c:if test="${inte.interview.status=='通知面试'}">
                    <a href="${pageContext.request.contextPath}/user/aodInterview?interview.id=${inte.interview.id}&interview.userStatus=同意面试">同意面试</a><a
                        href="${pageContext.request.contextPath}/user/aodInterview?interview.id=${inte.interview.id}&interview.userStatus=拒绝面试">拒绝面试</a>
                </c:if>
                <c:if test="${inte.interview.status=='录用'}">
                    <a href="${pageContext.request.contextPath}/user/aodInterview?interview.id=${inte.interview.id}&interview.userStatus=同意入职">同意入职</a>
                    <a href="${pageContext.request.contextPath}/user/aodInterview?interview.id=${inte.interview.id}&interview.userStatus=拒绝入职">拒绝入职</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
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

<form class="layui-form" id="attest" style="display: none;">
    <table class="layui-table">
        <tr>
            <td>请输入真实姓名</td>
            <td><input type="text" required lay-verify="required" class="layui-input" id="name"
                       name="name"></td>
        </tr>
        <tr>
            <td>请输入员工编号</td>
            <td><input type="text" required lay-verify="required|number" class="layui-input" id="employeeId"
                       name="employeeId"></td>
        </tr>
        <tr><td><button type="button" lay-submit lay-filter="attest" class="layui-btn layui-btn-normal">提交</button></td></tr>
    </table>
</form>
<script>
    //Demo
    layui.use(['form', 'layer', 'element'], function () {
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


        form.on('submit(attest)', function (data) {


            var r = /^\+?[1-9][0-9]*$/;
            var b = r.test(data.field.employeeId);
            if (b) {
                $.post("${pageContext.request.contextPath}/user/attest", data.field, function (result) {
                    layer.alert(result, function () {
                        window.location.reload();
                    });
                });
            }else {
                layer.msg("用户编号必须为正整数");
            }
            // layer.msg(JSON.stringify(data.field));
            return false;
        });
    });


    function updatePass() {
        var layer = layui.layer;
        layer.open({
            title: '修改密码',
            type: 1,
            content: $("#updatePass")
        })
    }


    function attest() {
        var layer = layui.layer;
        layer.open({
            title: '实名验证',
            type: 1,
            content: $("#attest")
        })
    }
</script>
</html>
