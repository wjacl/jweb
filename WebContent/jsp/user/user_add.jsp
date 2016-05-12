<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newer.user.User" %>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" 
    uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" 
    uri="http://java.sun.com/jsp/jstl/functions" %> 

<%-- 加载国际化资源到 session 中，
	以 "mess"为名放在session中 --%>
<fmt:setBundle basename="i18n.message" 
    	scope="session" var="mess"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="user.add.title" bundle="${mess }" /></title>
</head>
<body>
	${fn:length(param.uid) }
	<h2><fmt:message key="user.add.title" bundle="${mess }" /></h2>
	<form action="<%=request.getContextPath() %>/user/add" method="post">
		<table>
			<tr>
				<td><fmt:message key="user.name" bundle="${mess }" /></td>
				<td><input type="text" name="name" value="${param.name }"/>
					<span style="color:red">${nameError }</span>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="user.account" bundle="${mess }" /></td>
				<td><input type="text" name="account" value="${param.account }"/>
					<span style="color:red">${accountError }</span>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="user.pwd" bundle="${mess }" /></td>
				<td><input type="password" name="pwd" value="${param.pwd }" />
					<span style="color:red">${pwdError }</span>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="user.type" bundle="${mess }" /></td>
				<td>
					<input type="radio" name="type" value="0"
					<c:if test='${param.type == "0" }'>
						checked
					</c:if>
					/><fmt:message key="user.type.admin" bundle="${mess }" />
					<input type="radio" name="type" value="1"
					<c:if test='${param.type == "1" }'>
						checked
					</c:if>
					/><fmt:message key="user.type.nomal" bundle="${mess }" />
					<span style="color:red">${typeError }</span>
					
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value='<fmt:message key="fun.sbumit" bundle="${mess }" />' /></td>
			</tr>
		</table>
			<span style="color:red">${error }</span>
	</form>
</body>
</html>