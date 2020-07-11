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
    <h1>课程列表</h1>
    <a href="CourseServlet?action=toAdd">新增课程</a>
    <br/>
    <div align="center">
	    
        <table width="400px">
            <tr>
                <th>课程名称</th>
                <th>上课时间</th>
                <th>任课教师</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${courses}" var="course" varStatus="id">
	            <tr>
	                <td align="center">${course.name}</td>
	                <td align="center">${course.time}</td>
	                <td align="center">${course.username}</td>
	                <td align="center">
	                    <a href="CourseServlet?action=toUpdate&id=${course.id}">修改</a>
	                    <a href="CourseServlet?action=delete&id=${course.id}" onclick="return doDel()">删除</a>
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
