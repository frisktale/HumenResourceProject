<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/11
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户简历</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/${requestScope.subMethod}" class="layui-form">
    <table class="layui-table">
        <tr>
            <th colspan="4"></th>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td>"${requestScope.resume.name}"</td>
            <td>性别</td>
            <td>${requestScope.resume.sex}</td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>
                ${requestScope.resume.age}岁
            </td>
            <td>学历</td>
            <td>
                ${requestScope.resume.education}
            </td>
        </tr>
        <tr>
            <td>联系方式</td>
            <td>${requestScope.resume.tel}
            </td>
            <td>电子邮箱</td>
            <td>${requestScope.resume.email}</td>
        </tr>
        <tr>
            <td>政治面貌</td>
            <td>${requestScope.resume.politicalStatus}
            </td>
            <td>上份工作</td>
            <td>${requestScope.resume.lastJob}
        </tr>

        <tr>
            <td>最低期望工资</td>
            <td>${requestScope.resume.expectedSalaryLow}
            </td>
            <td>最高期望工资</td>
            <td>${requestScope.resume.expectedSalaryHigh}
            </td>
        </tr>
        <tr>
            <td>工作经验</td>
            <td>${requestScope.resume.exp}
            </td>
            <td>兴趣爱好</td>
            <td>${requestScope.resume.hobby}</td>
        </tr>
    </table>
</form>


<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;
    });
</script>
</body>
</html>
