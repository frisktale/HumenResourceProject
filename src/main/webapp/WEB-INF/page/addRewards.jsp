<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加奖惩</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<form class="layui-form" method="post" action="${pageContext.request.contextPath}/admin/${requestScope.method}"
      onsubmit="return check()">
    <table class="layui-table">
        <input type="hidden" name="employeeId" value="${requestScope.employeeId}"/>
        <input type="hidden" name="id" value="${requestScope.reward.id}"/>
        <tr>
            <td>时间</td>
            <td><input required lay-verify="required" id="time" name="time" readonly="readonly" class="layui-input"
                       placeholder="输入奖惩时间" value="<fmt:formatDate value="${requestScope.reward.time}" pattern="yyyy-MM-dd HH:mm:ss"/> "></td>
        </tr>
        <tr>
            <td>金额</td>
            <td><input required lay-verify="required" value="${requestScope.reward.money}" id="money" name="money" class="layui-input"
                       placeholder="输入奖惩金额(区分正负)"></td>
        </tr>
        <tr>
            <td>原因</td>
            <td><input required lay-verify="required" value="${requestScope.reward.content}" name="content" class="layui-input" placeholder="输入奖惩原因"></td>
        </tr>
        <tr>
            <td><input type="submit" value="确定" class="layui-btn layui-btn-normal"></td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function check() {


        var value = $("#money").val().trim();
        if (value.length == 0) {
            $("#money").val("");
            alert("金额不可为空");
            return false;
        }
        if (isNaN(Number(value))) {
            alert("金额不是数字");
            $("#money").val("");
            return false;
        }
        return true;
    }

    layui.use('form', function () {
        var form = layui.form;

        /*//监听提交
        form.on('submit(formDemo)', function(data){
            return true;
        });*/
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#time'
        });
    });
</script>
</html>
