<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书类别新增</title>
</head>
<body>
	<h2>图书类别新增</h2>
	<form action="<%=request.getContextPath() %>/book/type/add" method="post">
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
				/>
					&nbsp;
					<span style="color: red" >
						<%
							String message = (String)request.getAttribute("message");
							if(message != null){
								out.print(message);
							}
						%>
					</span>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>