<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.newer.book.BookType" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书类别查询</title>
</head>
<body>
	<h2>图书类别查询</h2>
	<form action="<%=request.getContextPath() %>/book/type/query" method="post">
		<table>
			<tr>
				<td>类别名称：</td>
				<td><input type="text" name="name" 
				<%
					String name = request.getParameter("name");
					if(name != null){
						out.print(" value=\"" + name + "\"");
					}
				%>
				/></td>
				<td><input type="submit" value="查询" />
				&nbsp;&nbsp;
					<a href="<%=request.getContextPath() %>/jsp/book/book_type_add.jsp">
						<input type="button" value="新增" />
					</a>
				</td>
			</tr>
		</table>
	</form>
	
	<br>
	<table>
		<tr>
			<th>ID</th>
			<th>类别名称</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<%
			List<BookType> list = (List<BookType>)request.getAttribute("list");
			if(list != null && list.size() > 0){
				for(BookType bt : list){
		%>
				<tr>
					<td><%=bt.getId() %></td>
					<td><%=bt.getName() %></td>
					<td><%=bt.getCreateUserName() %></td>
					<td><%=bt.getCreateTime() %></td>
					<td>
						<a href="<%=request.getContextPath() %>/book/type/update?id=<%=bt.getId() %>">修改</a>
						&nbsp;
						<a href="<%=request.getContextPath() %>/book/type/delete?id=<%=bt.getId() %>">删除</a>
					</td>
				</tr>
		<% 	
				}
			}
			else{
				String message = (String)request.getAttribute("message");
				if(message == null){
					message = "没有符合条件的数据";
				}
		%>
			<tr>
				<td colspan="5"><%=message %></td>
			</tr>
		<% 
			}
		%>
		
	</table>
</body>
</html>