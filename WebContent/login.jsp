<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>教师管理系统</title>
</head>
<body>
	<div align="center">
		<br>
		<H1>用户登录</H1>
		<form name="form1" method="post" action="LoginServlet?action=login" onsubmit="return checkLogin()">
			<table width="90%">
				<tr>
					<td width="50%" height="30" align="right">用户名：</td>
					<td width="50%" height="30" align="left"><input type="text"
						name="username" id="username"></td>
				</tr>
				<tr>
					<td width="50%" height="30" align="right">密码：</td>
					<td width="50%" height="30" align="left"><input
						type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td width="50%" height="30" align="right">用户类型：</td>
					<td width="50%" height="30" align="left">
						<select name="type">
							<option value="1">管理员</option>
							<option value="2">教师</option>
						</select>
					</td>
				</tr>
				<td width="100%" height="40" align="center" colspan="2"><input
					type="submit" name="sub" value="登录"></td>
				</tr>
			</table>
		</form>
	</div>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		function checkLogin() {
			var username = $("#username").val();
			var password = $("#password").val();
			if (username == "" || password == "") {
				alert("请输入用户名、密码");
				return false;
			} else {
				return true;
			}
		}
	</script>
</body>
</html>