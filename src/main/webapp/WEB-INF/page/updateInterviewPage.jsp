<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改面试详情</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<form class="layui-form" method="post" action="${pageContext.request.contextPath}/interview/updateInterview">
    <input type="hidden" name="interview.id" value="${requestScope.interview.interview.id}">
    <table class="layui-table">
        <tr>
            <td>面试时间</td>
            <td><input type="text"
                       value="<fmt:formatDate value="${requestScope.interview.interview.interviewTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/> "
                       name="interview.interviewTime"
                       class="layui-input" id="interviewTime" autocomplete="off" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td>
                状态
            </td>
            <td>
                ${requestScope.interview.interview.status}
            </td>
        </tr>
        <tr>
            <td>
                用户状态
            </td>
            <td>
                ${requestScope.interview.interview.userStatus}
            </td>
        </tr>
        <tr>
            <td>
                操作
            </td>
            <td>
                <select name="interview.status">
                    <option value="拒绝录用">拒绝录用</option>
                    <option value="通知面试">通知面试</option>
                    <option value="录用">录用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input class="layui-btn layui-btn-normal" type="submit" value="确认">
                <c:if test="${requestScope.interview.interview.userStatus eq '同意入职'&& empty requestScope.interview.employee.id}">
                <input type="button" class="layui-btn layui-btn-normal" value="正式录用" onclick="hire()"/></td>

            <script>
                function hire() {
                    var layer = layui.layer;
                    layer.open({
                        type: 1,//弹出框类型
                        title: '详细操作',
                        content: $('#hire'),
                        end: function () {
                            window.location.reload();
                        }
                    });
                    var layer = layui.layer;
                    var form = layui.form;
                    form.on('submit(formDemo)', function (data) {
                        layer.alert(JSON.stringify(data.field));
                        if (data.field["salary"] <= 0) {
                            layer.msg("工资不可为负数");
                            return false;
                        }
                        return true;
                    });
                }
            </script>
            </c:if>
            <td><input class="layui-btn layui-btn-normal" type="button" lay-submit lay-filter="close" value="关闭面试信息">
            </td>
        </tr>
    </table>
</form>
<script>

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#interviewTime', //指定元素
            type: 'datetime',
            format: 'yyyy年MM月dd日 HH时mm分ss秒'
        });
    });

    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(close)', function (data) {
            layer.msg(JSON.stringify(data.field));
            $.post("${pageContext.request.contextPath}/interview/closeInterview", data.field, function (result) {
                layer.alert(result);
            });
            return false;
        });


    });


</script>
</body>

<form class="layui-form" method="post" style="display: none" id="hire"
      action="${pageContext.request.contextPath}/interview/hire">
    <input type="hidden" name="inteId" value="${requestScope.interview.interview.id}"/>
    <table class="layui-table">
        <tr>
            <td>薪资</td>
            <td><input type="text" name="salary" required lay-verify="required|number"
                       placeholder="请输入薪资"
                       autocomplete="off" class="layui-input"/></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                <select name="status" class="layui-form-select" required
                        lay-verify="required">
                    <option value="在职">在职</option>
                    <option value="试用">试用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">确认</button>
            </td>
        </tr>
    </table>
</form>
</html>
