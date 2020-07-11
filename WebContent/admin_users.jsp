<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
    <title>教师管理系统</title>
</head>
<body>
<jsp:include page="admin_main_nav.jsp"/>

<div style="text-align: center">
    <h1>用户列表</h1>
    <a href="admin_user_add.jsp">新增用户</a>
    <br/>
    <div align="center">
        <table width="400px">
            <tr>
                <th>用户名</th>
                <th>用户类型</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="id">
	            <tr>
	                <td align="center">${user.username}</td>
	                <c:if test="${user.type == 1}">
	                	<td align="center">管理员</td>
	                </c:if>
	                <c:if test="${user.type == 2}">
	                	<td align="center">教师</td>
	                </c:if>
	                <td align="center">
	                    <a href="UserServlet?action=toUpdate&id=${user.id}">修改</a>
	                    <a href="UserServlet?action=delete&id=${user.id}" onclick="return doDel()">删除</a>
	                </td>
	            </tr>
            </c:forEach>

        </table>

    </div>

</div>
<script>

    // 确定删除
    function doDel() {
        return window.confirm("是否确定删除");
    }
</script>
</body>
</html>
