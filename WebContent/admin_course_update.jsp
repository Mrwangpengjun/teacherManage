<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="admin_main_nav.jsp"/>

<div style="text-align: center">
    <h1>课程信息修改</h1>
    <div>

        <form method="post" action="CourseServlet?action=doUpdate">
        	<input type="hidden" name="id" value="${course.id}"/>
            <label>课程名称:</label>
            <input name="name" placeholder="请输入课程名称" id="teamName" value="${course.name}"/>
            <br>
            <label>上课时间:</label>
            <input name="time" placeholder="请输入上课时间" id="teamDept" value="${course.time}"/>
            <br>
            <label>任课教师:</label>
            <select name="userid" id="teacher">
				<option value="">请选择任课教师</option>
				<c:forEach items="${users}" var="user">
					<option value="${user.id}" <c:if test="${user.id == course.userid}">selected</c:if>>${user.username}</option>
				</c:forEach>
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
