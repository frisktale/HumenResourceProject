<%--
  Created by IntelliJ IDEA.
  User: frisktale
  Date: 2018/10/22
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>权限错误</title>
</head>
<body>
<script type="text/javascript">
    var t=4;//设定跳转的时间
    setInterval("refer()",1000); //启动1秒定时
    function refer(){
        if(t==0){
            location="${pageContext.request.contextPath}/loginPage"; //#设定跳转的链接地址
        }
        document.getElementById('show').innerHTML=""+t+"秒后跳转"; // 显示倒计时
        t--; // 计数器递减
    }
</script>
你的权限不足<br/>
<span id="show">5秒后跳转</span><br/>
<a href="${pageContext.request.contextPath}/loginPage">点此跳转至登陆页面</a>
</body>
</html>