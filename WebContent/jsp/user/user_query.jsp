<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>  
<%@ page import="com.newer.user.User" %> 
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" 
    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	<h2>用户管理</h2>
	<form action="${pageContext.request.contextPath }/user/query" method="post">
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
		
		<c:if test="${not empty list }">
			<c:forEach items="${list }" var="user">
				<tr>
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>${user.account }</td>
					<td>${user.type == "0" ? "系统管理员" : "业务用户"}</td>
					<td>${user.createUserName }</td>
					<td>
						<fmt:formatDate value="${user.createDate }" pattern="YYYY年MM月DD日 "/>
					</td>
					<td>
						<a href="${pageContext.request.contextPath }/user/update?id=${user.id}">
							修改
						</a>
						<a href="${pageContext.request.contextPath }/user/delete?id=${user.id}">
							删除
						</a>
					</td>
				</tr>	
			</c:forEach>	
		</c:if>
	</table>
</body>
</html>