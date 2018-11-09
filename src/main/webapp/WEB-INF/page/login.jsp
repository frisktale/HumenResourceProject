<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/8
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<div class="layui-container">
    <div class="layui-row">
        <form method="post" class="layui-form" action="${pageContext.request.contextPath}/login">
            <table class="layui-table" lay-skin="line">
                <tr>
                    <td><label class="layui-form-label">用户名</label></td>
                    <td><input type="text" name="username" id="username" required lay-verify="required"
                               placeholder="请输入用户名"
                               autocomplete="off" class="layui-input"/></td>
                    <%--<td><span id="usernamemsg"></span></td>--%>

                </tr>
                <tr>
                    <td><label class="layui-form-label">密码</label></td>
                    <td><input type="password" name="password" id="password" required lay-verify="required"
                               placeholder="请输入密码"
                               autocomplete="off" class="layui-input"/></td>
                </tr>

                <tr>
                    <td><input type="submit"  lay-submit lay-filter="login" value="登陆" class="layui-btn layui-btn-normal"></td>
                    <td><a href="${pageContext.request.contextPath}/registerPage"><input type="button" value="点此注册" class="layui-btn layui-btn-normal"></a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    layui.use('form', function () {
        var form = layui.form;
        form.on('select(login)',function (data) {
           return true;
        });
    });

    $("#username").blur(function () {
        var val = $(this).val();
        $.ajax({
            url: "${pageContext.request.contextPath}/checkName",
            data: {"name": val},
            method: "POST",
            success: function (data) {
                if (data == "n") {
                    layer.tips('用户名不存在', '#username');
                    // $("#usernamemsg").text("用户名不存在");
                } else {
                    $("#usernamemsg").text(" ");
                }
            }
        })
    });
</script>
</body>
</html>
