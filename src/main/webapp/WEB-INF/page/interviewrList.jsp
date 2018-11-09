<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>面试列表</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a></li>
</ul>
<table class="layui-table">
    <tr>
        <th>求职者姓名</th>
        <th>投递时间</th>
        <th>面试时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <tr>
        <td colspan="8">未读</td>
    </tr>
    <c:forEach items="${requestScope.interviews}" var="inte">
        <c:choose>
            <c:when test="${not inte.interview.isRead}">
                <tr>
                    <td>${inte.user.username}</td>
                    <td><fmt:formatDate value="${inte.interview.deliveryTime}" type="both"/></td>
                    <td><fmt:formatDate value="${inte.interview.interviewTime}" type="both"/></td>
                    <td>
                            ${inte.interview.userStatus}
                    </td>
                    <td><input class="layui-btn layui-btn-normal" type="button" value="查看简历" onclick="showResume('${inte.user.id}')">
                            <%--<a href="${pageContext.request.contextPath}/interview/userResume?uId=${inte.user.id}">查看简历</a>--%>
                            <%--<a href="${pageContext.request.contextPath}/interview/updateInterviewPage?id=${inte.interview.id}">详细操作</a>--%>
                        <input class="layui-btn layui-btn-normal" type="button" value="详细操作" onclick="updateInterview('${inte.interview.id}')">
                    </td>
                        <%--<td><a href="${pageContext.request.contextPath}/emp/updatePage?id=${emp.employee.id}">修改</a> </td>--%>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
    <tr>
        <td colspan="8">已读</td>
    </tr>
    <c:forEach items="${requestScope.interviews}" var="inte">
        <c:choose>
            <c:when test="${inte.interview.isRead}">
                <tr>
                    <td>${inte.user.username}</td>
                    <td><fmt:formatDate value="${inte.interview.deliveryTime}" type="both"/></td>
                    <td><fmt:formatDate value="${inte.interview.interviewTime}" type="both"/></td>
                    <td>
                            ${inte.interview.status}
                    </td>
                    <td><input class="layui-btn layui-btn-normal" type="button" value="查看简历" onclick="showResume('${inte.user.id}')">
                        <%--<a href="${pageContext.request.contextPath}/interview/userResume?uId=${inte.user.id}">查看简历</a>--%>
                        <%--<a href="${pageContext.request.contextPath}/interview/updateInterviewPage?id=${inte.interview.id}">详细操作</a>--%>
                        <input class="layui-btn layui-btn-normal" type="button" value="详细操作" onclick="updateInterview('${inte.interview.id}')">
                    </td>
                        <%--<td><a href="${pageContext.request.contextPath}/emp/updatePage?id=${emp.employee.id}">修改</a> </td>--%>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
</table>
</body>

<script>
    layui.use(['form','layer'], function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            form.render();
            return true;
        });
    });
    function showResume(uId) {
        var layer = layui.layer;
        layer.open({
            type: 2,//弹出框类型
            title: '用户简历',
            area: ['60%', '90%'],
            shift: 1,
            content: "${pageContext.request.contextPath}/interview/userResume?uId=" + uId
        });
    }

    function updateInterview(id) {
        var layer = layui.layer;
        layer.open({
            type: 2,//弹出框类型
            title: '详细操作',
            area: ['60%', '90%'],
            shift: 1,
            content: "${pageContext.request.contextPath}/interview/updateInterviewPage?id=" + id,
            end: function () {
                window.location.reload();
            }
        });
    }
</script>

</html>
