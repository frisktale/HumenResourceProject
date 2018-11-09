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
    <title>${requestScope.title}</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../layui/layui.js"></script>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/${requestScope.subMethod}" class="layui-form">
    <c:if test="${not empty requestScope.resume}">
        <input type="hidden" name="id" value="${requestScope.resume.id}"/>
    </c:if>
    <table class="layui-table">
        <tr>
            <th colspan="4"></th>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td><input required lay-verify="required" name="name" value="${requestScope.resume.name}"
                       class="layui-input"></td>
            <td>性别</td>
            <td><input type="radio" name="sex" value="男" title="男" <c:if
                    test="${'男'==requestScope.resume.sex||empty requestScope.resume}"> checked</c:if>/>
                <input type="radio" name="sex" value="女" title="女"<c:if
                        test="${'女'==requestScope.resume.sex}"> checked</c:if>/></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>
                <select name="age" required>
                    <c:if test="${not empty requestScope.resume}">
                        <option value="${requestScope.resume.age}">${requestScope.resume.age}岁</option>
                    </c:if>
                    <c:forEach begin="1" end="80" varStatus="vs">
                        <option value="${vs.index}">${vs.index}岁</option>
                    </c:forEach>
                </select>
            </td>
            <td>学历</td>
            <td>
                <select name="education">
                        <c:if test="${not empty requestScope.resume}">
                            <option value="${requestScope.resume.education}">${requestScope.resume.education}</option>
                        </c:if>
                    <option value="小学">小学</option>
                    <option value="初中">初中</option>
                    <option value="高中">高中</option>
                    <option value="大专">大专</option>
                    <option value="大学本科">大学本科</option>
                    <option value="研究生">研究生</option>
                    <option value="硕士">硕士</option>
                    <option value="博士">博士</option>
                    <option value="博士后">博士后</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>联系方式</td>
            <td><input required lay-verify="required" name="tel" value="${requestScope.resume.tel}" class="layui-input">
            </td>
            <td>电子邮箱</td>
            <td><input required lay-verify="required" name="email" value="${requestScope.resume.email}"
                       class="layui-input"></td>
        </tr>
        <tr>
            <td>政治面貌</td>
            <td>
                <select name="politicalStatus">
                        <c:if test="${not empty requestScope.resume}">
                            <option value="${requestScope.resume.politicalStatus}">${requestScope.resume.politicalStatus}</option>
                        </c:if>
                    <option value="中共党员">中共党员</option>
                    <option value="共青团员">共青团员</option>
                    <option value="群众">群众</option>
                </select>
            </td>
            <td>上份工作</td>
            <td><input class="layui-input"  value="${requestScope.resume.lastJob}" autocomplete="off" required lay-verify="required" name="lastJob"></td>
        </tr>

        <tr>
            <td>最低期望工资</td>
            <td>
                <select name="expectedSalaryLow" required>
                    <c:if test="${not empty requestScope.resume}">
                        <option value="${requestScope.resume.expectedSalaryLow}">${requestScope.resume.expectedSalaryLow}元</option>
                    </c:if>
                    <c:forEach begin="5" end="15" varStatus="vs">
                        <option value="${(vs.index)*1000}">${(vs.index)*1000}元</option>
                    </c:forEach>
                </select>
            </td>
            <td>最高期望工资</td>
            <td>
                <select name="expectedSalaryHigh" required>
                    <c:if test="${not empty requestScope.resume}">
                        <option value="${requestScope.resume.expectedSalaryHigh}">${requestScope.resume.expectedSalaryHigh}元</option>
                    </c:if>
                    <c:forEach begin="5" end="15" varStatus="vs">
                        <option value="${(vs.index)*1000}">${(vs.index)*1000}元</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>工作经验</td>
            <td><input class="layui-input" required lay-verify="required" name="exp" value="${requestScope.resume.exp}" ></td>
            <td>兴趣爱好</td>
            <td><input class="layui-input" required lay-verify="required" name="hobby" value="${requestScope.resume.hobby}"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="layui-btn" value="保存" lay-submit lay-filter="formDemo"></td>
            <td colspan="2"><input type="button" class="layui-btn layui-btn-normal"
                                   onclick="javascript:history.back(-1);" value="返回"></td>
        </tr>
    </table>
</form>


<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            form.render();
            return true;
        });
    });
</script>
</body>
</html>
