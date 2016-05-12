<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>  
<%@ page import="com.newer.user.User" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	<h2>用户管理</h2>
	<form action="<%=request.getContextPath()%>/user/query" method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"></td>
				<td>账号：</td>
				<td><input type="text" name="account"></td>
				<td>用户类别：</td>
				<td>
					<select name="type">
						<option value="">请选择</option>
						<option value="0">系统管理员</option>
						<option value="1">业务用户</option>
					</select>
				</td>
				<td><input type="submit" value="查询"></td>
			</tr>
		</table>
	</form>
	
	<table>
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>账号</th>
			<th>类别</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		
		<%
			List<User> list = (List<User>) request.getAttribute("list");
			if(list != null && list.size() > 0){
				for(User user : list){
		%>
				<tr>
					<td><%=user.getId() %></td>
					<td><%=user.getName() %></td>
					<td><%=user.getAccount() %></td>
					<td><%= User.TYPE_SYS_MANAGER.equals(
							user.getType()) ? "系统管理员" : "业务用户" %></td>
					<td>
						<% String cuname = user.getCreateUserName();
							if(cuname == null){
								cuname = "";
							}%>
						<%=cuname %></td>
					<td><%=user.getCreateDate() %></td>
					<td>
						<a href="<%=request.getContextPath()%>/user/update?id=<%=user.getId() %>">
							修改
						</a>
						<a href="<%=request.getContextPath()%>/user/delete?id=<%=user.getId() %>">
							删除
						</a>
					</td>
				</tr>		
		<% 			
				}
			}
		%>
	</table>
</body>
</html>