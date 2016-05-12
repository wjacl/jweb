package com.newer.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserQueryServlet extends HttpServlet{

	private static final Logger LOGGER =
			LoggerFactory.getLogger(UserQueryServlet.class);

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		//1、接收请求参数
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String type = request.getParameter("type");

		//2、参数校验
		//查询的情况，一般可以不校验参数

		//3、业务处理
		//查询数据
		UserDao dao = new UserDao();
		try {
			List<User> list = dao.queryUsers(name, account, type);
			//将查到的数据传递到jsp
			request.setAttribute("list",list);
		} catch (SQLException e) {
			LOGGER.error("查询用户异常", e);
		}

		//转向-转发到查询界面
		request.getRequestDispatcher("/jsp/user/user_query.jsp")
			.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		this.doPost(request, response);
	}

}
