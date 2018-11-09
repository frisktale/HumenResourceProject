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
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminCenter">个人中心</a></li>
    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/depList">查看部门</a></li>
    <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/interview/showOffer">查看招聘信息</a>
    </li>
</ul>
<form id="offers" class="layui-form">
    <table class="layui-table">
        <tr>
            <th>选择</th>
            <th>招聘详情</th>
            <th>招聘部门</th>
            <th>招聘岗位</th>
            <th>招聘数量</th>
            <th>截止时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.offers}" var="off">
            <tr>
                <td><input name="ids" type="checkbox" value="${off.offers.id}"/></td>
                <td>${off.offers.message}</td>
                <td>${off.department.name}</td>
                <td>${off.position.name}</td>
                <td>${off.offers.number}</td>
                <td><fmt:formatDate value="${off.offers.effectiveTime}" type="both"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/interview/showInterviewByOId?oId=${off.offers.id}"><input
                            type="button" class="layui-btn-normal layui-btn" value="查看面试信息"></a>
                    <input type="button" class="layui-btn-normal layui-btn" value="修改"
                           onclick="updateOffer('${off.offers.id}','${off.offers.message}','${off.offers.number}',
                                   '<fmt:formatDate value="${off.offers.effectiveTime}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>')">
                </td>

            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" class="layui-btn layui-btn-normal" value="添加招聘" onclick="addOffer()"/></td>
            <td><input type="button" class="layui-btn layui-btn-normal" value="删除" onclick="deleteOffer()"/></td>
        </tr>
    </table>
</form>
</body>

<form id="addOffers" method="post" class="layui-form"
      style="display: none">
    <input type="hidden" id="oId" name="offers.id" value=""/>
    <table class="layui-table">
        <tr>
            <td>招聘详情</td>
            <td>
                <textarea id="content" name="offers.message" required lay-verify="required" placeholder="请输入招聘信息"
                          autocomplete="off" class="layui-textarea"></textarea>
            </td>
        </tr>
        <tr>
            <td>所属部门</td>
            <td>
                <select id="department" name="department.id" lay-filter="department" required
                        lay-verify="required">
                    <c:forEach items="${requestScope.department}" var="dep">
                        <option value="${dep.department.id}">${dep.department.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>所属职位</td>
            <td>
                <select name="position.id"  id="position" class="layui-form-select" required
                        lay-verify="required">
                </select>
            </td>
        </tr>
        <tr>
            <td>人数</td>
            <td><input type="text" id="number" name="offers.number" required lay-verify="required|number"
                       placeholder="请输入招聘人数"
                       autocomplete="off" class="layui-input">
            </td>
        </tr>
        <tr>
            <td>截至时间</td>
            <td><input type="text" class="layui-input" required lay-verify="required" readonly autocomplete="off"
                       name="offers.effectiveTime" id="date"></td>
        </tr>
        <tr>
            <td><input type="button" value="确认" lay-submit lay-filter="formDemo" class="layui-btn layui-btn-normal">
            </td>
        </tr>
    </table>
</form>

<script>
    layui.use(['layer', 'form', 'laydate'], function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#date',
            type: 'datetime'
        });
        var form = layui.form;
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

    function addOffer() {
        $("#oId").val("");
        $("#content").val("");
        $("#number").val("");
        $("#date").val("");
        var layer = layui.layer;
        var open = layer.open({
            type: 1,
            area: ['500px', '700px'],
            title: '添加招聘信息',
            content: $('#addOffers')
        }, function () {

        });
        var form = layui.form;
        form.on('submit(formDemo)', function (data) {

            var type="^[0-9]*[1-9][0-9]*$";
            var r=new RegExp(type);
            var flag=r.test(data.field['offers.number']);
            if(!flag){
                layui.layer.msg("人数应该为正整数");
                return false;
            }

            if (data.field['position.id'] == "") {
                layui.layer.msg("请选择职位");
                return false;
            }

            // alert(offer);
            $.post('${pageContext.request.contextPath}/interview/addOffer', data.field, function (result) {
                layer.close(open);
                layer.alert(result, function () {
                    window.location.reload();
                });
            });
            return false;
        });
    }

    function deleteOffer() {
        var serialize = $("#offers").serialize();
        $.post('${pageContext.request.contextPath}/interview/deleteOffer', serialize, function (result) {
            layer.alert(result, function () {
                window.location.reload();
            });
        });
    }

    function updateOffer(id, message, number, date) {
        /*alert(id);
        alert(message);
        alert(number);
        alert(date);*/
        $("#oId").val(id);
        $("#content").val(message);
        $("#number").val(number);
        $("#date").val(date);
        var open = layer.open({
            type: 1,
            area: ['500px', '700px'],
            title: '修改招聘信息',
            content: $('#addOffers')
        });



        var form = layui.form;
        form.on('submit(formDemo)', function (data) {

            var type="^[0-9]*[1-9][0-9]*$";
            var r=new RegExp(type);
            var flag=r.test(data.field['offers.number']);
            if(!flag){
                layui.layer.msg("人数应该为正整数");
                return false;
            }

            if (data.field['position.id'] == "") {
                layui.layer.msg("请选择职位");
                return false;
            }

            // alert(offer);
            $.post('${pageContext.request.contextPath}/interview/updateOffer', data.field, function (result) {
                layer.close(open);
                layer.alert(result, function () {
                    window.location.reload();
                });
            });
            return false;
        });
    }
</script>
</html>
