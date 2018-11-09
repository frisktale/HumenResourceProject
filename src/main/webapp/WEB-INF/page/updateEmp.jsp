<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/10
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="../layui/layui.js"></script>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<head>
    <title>${requestScope.title}</title>
</head>
<body>
<form method="post" class="layui-form" action="${pageContext.request.contextPath}/${requestScope.method}">
    <input type="hidden" name="employee.id" value="${requestScope.employee.employee.id}"/>
    <table class="layui-table">
        <tr>
            <td>员工名称</td>
            <td><input value="${requestScope.employee.employee.name}" name="employee.name" required
                       lay-verify="required"
                       autocomplete="off" class="layui-input"/></td>
        </tr>
        <tr>
            <td>员工当前状态</td>
            <td>
                <select name="employee.status" class="layui-form-select" required
                        lay-verify="required">
                    <option value="${requestScope.employee.employee.status}">${requestScope.employee.employee.status}</option>
                    <option value="在职">在职</option>
                    <option value="离职">离职</option>
                    <option value="试用">试用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>所在部门</td>
            <td>
                <select name="department.id" class="layui-form-select" lay-filter="department" required
                        lay-verify="required">
                    <c:if test="${not empty requestScope.employee}">
                        <option value="${requestScope.employee.department.id}">${requestScope.employee.department.name}</option>
                    </c:if>
                    <c:forEach items="${requestScope.department}" var="dep">
                        <option value="${dep.department.id}">${dep.department.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>所在职位</td>
            <td>
                <select name="position.id" id="position" lay-filter="position" class="layui-form-select" required
                        lay-verify="required">
                    <c:if test="${not empty requestScope.employee}">
                        <option value="${requestScope.employee.position.id}">${requestScope.employee.position.name}</option>
                    </c:if>
                </select>
            </td>
        </tr>
        <tr>
            <td>工资</td>
            <td><input value="${requestScope.employee.employee.salary}" name="employee.salary" required
                       lay-verify="required|number"
                       autocomplete="off" class="layui-input"/></td>
        </tr>

        <tr>
            <td colspan="2"><input lay-submit lay-filter="formDemo" type="submit" value="确定" class="layui-btn"></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;


        form.on('submit(formDemo)', function (data) {
            // layer.msg(data["employee.salary"]);
            if (data.field["employee.salary"] <= 0) {
                layer.msg("工资不可为负数");
                return false;
            }
            return true;
        });

        form.render('select');
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
</script>
</body>
</html>
