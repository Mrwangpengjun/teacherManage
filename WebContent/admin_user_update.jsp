<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="admin_main_nav.jsp"/>

<div style="text-align: center">
    <h1>用户信息修改</h1>
    <div>

        <form method="post" action="UserServlet?action=doUpdate">
        	<input type="hidden" name="id" value="${user.id}"/>
            <label>用户名:</label>
            <input name="username" placeholder="请输入用户名" id="teamName" value="${user.username}"/>
            <br>
            <label>密码:</label>
            <input name="password" placeholder="请输入密码" id="teamDept" type="password" value="${user.password}"/>
            <br>
            <label>用户类型:</label>
            <select name="type">
				<option value="1" <c:if test="${user.type==1}">selected</c:if>>管理员</option>
				<option value="2" <c:if test="${user.type==2}">selected</c:if>>教师</option>
			</select>
			<br>
            <button onclick="doAdd()">提交</button>
        </form>
    </div>

</div>
<script>
    function doAdd() {
        let teamName = document.getElementById('teamName').value.trim();
        let teamDept = document.getElementById('teamDept').value.trim();
        if (teamName.length <= 0 || teamDept.length <= 0 ){
            alert('请输入信息');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
