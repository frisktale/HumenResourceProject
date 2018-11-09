<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>招聘列表</title>
</head>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/userCenter">个人中心</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/user/showOffers">查看招聘信息</a></li>
</ul>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<form action="${pageContext.request.contextPath}/user/submitResumePage" class="layui-form">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>招聘详情</th>
            <th>招聘部门</th>
            <th>招聘岗位</th>
            <th>招聘数量</th>
            <th>截止时间</th>
        </tr>
        <c:forEach items="${requestScope.offers}" var="off" varStatus="vs">
            <tr>
                <td><input type="radio" name="oId" value="${off.offers.id}"
                           <c:if test="${vs.index==0}">checked</c:if>
                /></td>
                <td>${off.offers.message}</td>
                <td>${off.department.name}</td>
                <td>${off.position.name}</td>
                <td>${off.offers.number}</td>
                <td><fmt:formatDate value="${off.offers.effectiveTime}" type="both"/></td>
            </tr>
        </c:forEach>
    </table>

    <c:choose>
        <c:when test="${empty requestScope.resume}">
            <input type="button" value="您还没有简历,请添加" class="layui-btn layui-btn-normal" onclick="addResume()">
            <script>
                function addResume() {
                    var layer = layui.layer;
                    layer.open({
                        title: "添加简历",
                        type: 2,
                        content: "${pageContext.request.contextPath}/user/addResumePage",
                        area: ['900px', '700px'],
                        end: function () {
                            window.location.reload();
                        }
                    });
                }
            </script>
        </c:when>
        <c:otherwise>
            <input type="button" class="layui-btn layui-btn-normal" value="查看和修改简历信息" onclick="updateResume()">
            <script>
                function updateResume() {
                    var layer = layui.layer;
                    layer.open({
                        title: "添加简历",
                        type: 2,
                        content: "${pageContext.request.contextPath}/user/updateResumePage",
                        area: ['900px', '600px'],
                        end: function () {
                            window.location.reload();
                        }
                    });
                }
            </script>
            <input lay-submit lay-filter="formDemo" class="layui-btn layui-btn-normal" type="button" value="提交简历">
        </c:otherwise>
    </c:choose>
</form>
<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer;
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            $.post("${pageContext.request.contextPath}/user/submitResumePage",
                data.field,function (result) {
                    layer.alert(result,function () {
                        window.location.reload();
                    })
                }
            );
            layer.msg(JSON.stringify(data.field));
            return true;
        });
    });
</script>
</body>
</html>
