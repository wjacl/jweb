<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newer.user.User" %>
<%@ page import="com.newer.util.Constants" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%">
	<tr>
		<td>
		<b>图书管理系统</b>
		</td>
		<td align="right">
			<%
				User user = (User) request.getSession().getAttribute(Constants.CURR_USER);
			%>
			<%=user.getName() %>,你好！&nbsp;&nbsp;
			<a href="">退出</a>
		</td>
	</tr>
	</table>
	<p>
		<a href="<%=request.getContextPath() %>/book/type/query">图书类别管理</a>&nbsp;
		<a href="xx">图书管理</a>&nbsp;
		<a href="xx">读者管理</a>&nbsp;
		<a href="xx">读者管理</a>&nbsp;
	</p>
</body>
</html>