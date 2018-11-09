<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/9
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门信息</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/emp/employeeCenter">个人中心</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/dep/showDepartment">查看部门</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/emp/showEmp">查看所在部门的其他职员</a></li>
</ul>
<div class="layui-form">
    <div class="layui-input-block">
        <select name="positionId" id="department" lay-filter="department" class="layui-form-select">
            <c:forEach items="${requestScope.department}" var="dep">
                <option value="${dep.department.id}">${dep.department.name}</option>
            </c:forEach>
        </select>
        <table id="position" class="layui-table">
            <tr>
                <th>职位名称</th>
                <th>创建时间</th>
            </tr>
        </table>

    </div>
</div>
</body>


<script type="text/javascript">


    layui.use('form', function () {
        var form = layui.form;
        form.render('select');
        form.on('select(department)', function (data) {

            $("#showEmpByDep").click(function () {
                window.location.href = "${pageContext.request.contextPath}/dep/showEmp?type=1&id=" + data.value;
                // alert("showEmpByDep"+data.value);
            });

            $.ajax({
                url: "${pageContext.request.contextPath}/dep/getPositions",
                method: "POST",
                data: {"id": data.value},
                success: function (result) {
                    var tb = document.getElementById("position");
                    var rowNum = tb.rows.length;
                    for (i = 1; i < rowNum - 1; i++) {
                        tb.deleteRow(i);
                        rowNum = rowNum - 1;
                        i = i - 1;
                    }
                    var parse = result;

                    for (var i = 0; i < parse.length; i++) {
                        var pos = parse[i];
                        $("#position tr:eq(0)").after("<tr><td>" + pos.name + "</td><td>" + pos.createTime + "</td> </tr>");
                    }
                    form.render();
                }
            });
        });
    });
</script>
</html>
