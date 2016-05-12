<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newer.user.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
</head>
<body>
	<h2>用户修改</h2>
	
	<%
		User user = (User) request.getAttribute("user");
		if(user == null){
			String error = (String)request.getAttribute("error");
			if(error != null){
			%>
				<h3><%=error %></h3>
			<% 
			}
			else{
			%>
				<h3>没有对应的用户</h3>
			<% 
			}
			%>
			<a href="<%=request.getContextPath() %>/user/query">返回</a>
			<% 
		}
		else {
			
	%>
	<form action="<%=request.getContextPath() %>/user/update" method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name" 
				<% 
					String name = user.getName();
					if(name != null){
				%>
					value="<%=name%>"
				<%
					}
				%>
				/>
					<%
						String nameError = (String) request
							.getAttribute("nameError");
						if(nameError != null){
					%>
						<span style="color:red"><%=nameError %></span>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>账号：</td>
				<td><input type="text" name="account" 
				<% 
					String account = user.getAccount();
					if(account != null){
				%>
					value="<%=account%>"
				<%
					}
				%>
				/>
					<%
						String accountError = (String) request.getAttribute("accountError");
						if(accountError != null){
					%>
						<span style="color:red"><%=accountError %></span>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="pwd"  
				<% 
					String pwd = user.getPwd();
					if(pwd != null){
				%>
					value="<%=pwd%>"
				<%
					}
				%>
				/>
					<%
						String pwdError = (String) request.getAttribute("pwdError");
						if(pwdError != null){
					%>
						<span style="color:red"><%=pwdError %></span>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>用户类别：</td>
				<td>
					<%
						String type = user.getType();
					%>
					<input type="radio" name="type" value="0"
					<%
						if("0".equals(type)){
					%>
						checked
					<%
						}
					%>
					/>系统管理员
					<input type="radio" name="type" value="1"
					<%
						if("1".equals(type)){
					%>
						checked
					<%
						}
					%>
					/>业务用户
					<%
						String typeError = (String) request.getAttribute("typeError");
						if(typeError != null){
					%>
						<span style="color:red"><%=typeError %></span>
					<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="id" value="<%=user.getId() %>" />
				</td>
				<td><input type="submit" value="提交" /></td>
			</tr>
		</table>
		<%
			String error = (String) request.getAttribute("error");
			if(error != null){
		%>
			<span style="color:red"><%=error %></span>
		<%
			}
		%>
	</form>
	<% 
		}
	%>
</body>
</html>