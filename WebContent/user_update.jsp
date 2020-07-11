<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="user_main_nav.jsp"/>

<div style="text-align: center">
    <h1>用户信息修改</h1>
    <div>

        <form method="post" action="UserServlet?action=userUpdate">
            <label>密码:</label>
            <input name="password" placeholder="请输入密码" id="teamDept" type="password" value="${user.password}"/>
			<br>
            <button onclick="doAdd()">提交</button>
        </form>
    </div>
</div>
<script>
    function doAdd() {
        let teamDept = document.getElementById('teamDept').value.trim();
        if (teamDept.length <= 0 ){
            alert('请输入信息');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
