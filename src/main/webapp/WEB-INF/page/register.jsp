<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/8
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<body>
<div class="layui-container">
    <div class="layui-row">
        <form method="post" action="${pageContext.request.contextPath}/register" class="layui-form" class="layui-table"
              lay-skin="line">
            <table class="layui-table">
                <tr>
                    <td><label class="layui-form-label">用户名</label></td>
                    <td><input type="text" name="username" id="username" required lay-verify="required"
                               placeholder="请输入用户名"
                               autocomplete="off" class="layui-input"/>
                </tr>
                <tr>
                    <td><label class="layui-form-label">密码</label></td>
                    <td><input type="password" name="password" id="password" required lay-verify="required"
                               placeholder="请输入密码"
                               autocomplete="off" class="layui-input"/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label">再次输入密码</label></td>
                    <td><input type="password" name="repassword" id="repassword" required lay-verify="required"
                               placeholder="请再次输入密码"
                               autocomplete="off" class="layui-input"/></td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" value="注册" id="register" lay-submit lay-filter="register"
                               class="layui-btn layui-btn-normal">
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/loginPage"><input type="button" value="点此登陆"
                                                                                      class="layui-btn layui-btn-normal"></a>
                    </td>

                </tr>
            </table>
        </form>
    </div>
</div>
</body>


<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var form = layui.form;
        var layer = layui.layer;

        form.on('submit(register)', function (data) {
            var field = data.field;



            if (field['password'] != field['repassword']) {
                layer.tips('两次密码不相同', '#repassword');
                return false;
            }
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
                    $("#register").removeAttr("disabled");
                } else {
                    var layer = layui.layer;
                    layer.tips('用户名已存在', '#username');
                    $("#register").attr("disabled", "true")
                }
            }
        })
    });
</script>

</body>
</html>
