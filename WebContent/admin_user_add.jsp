<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="admin_main_nav.jsp"/>

<div style="text-align: center">
    <h1>用户信息新增</h1>
    <div>

        <form method="post" action="UserServlet?action=doAdd">
            <label>用户名:</label>
            <input name="username" placeholder="请输入用户名" id="teamName"/>
            <br>
            <label>密码:</label>
            <input name="password" placeholder="请输入密码" id="teamDept" type="password"/>
            <br>
            <label>用户类型:</label>
            <select name="type">
				<option value="1">管理员</option>
				<option value="2">教师</option>
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
