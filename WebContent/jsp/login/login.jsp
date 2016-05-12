<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" +
		request.getServerName() + ":" 
		+ request.getServerPort() 
		+ request.getContextPath();
		
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>请登陆</h2>
	<form action="<%=basePath %>/login" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" name="username" /> 
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input type="password" name="pwd" /> 
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="登陆" /> 
				</td>
			</tr>
		</table>
	</form>
	<span style="color:red">
		<%
			String message = (String) request.getAttribute("message");
			if(message != null){
				out.print(message);
			}
		%>
	</span>
</body>
</html>