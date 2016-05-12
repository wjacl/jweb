package com.newer.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUpdateServlet extends HttpServlet{

	private static final Logger LOGGER =
			LoggerFactory.getLogger(UserUpdateServlet.class);
	/**
	 * 接收修改请求获得用户信息呈现出来
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{

		//1、接收请求参数
		request.setCharacterEncoding("UTF-8");
		String idStr = request.getParameter("id");

		int id = 0;
		if(idStr != null){
			try{
				id = Integer.parseInt(idStr);
			}catch(Exception e){
				LOGGER.error("转整型异常", e);
			}
		}


		//2、校验参数
		if(id == 0){
			//如果没有参数值,或参数值不是数值，就重定向的查询
			response.sendRedirect(
					request.getContextPath()
					+ "/user/query");
			return;
		}

		//3、业务处理
		//查询出指定id 的用户
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.getUserById(id);
		} catch (SQLException e) {
			LOGGER.error("查询用户异常", e);
			request.setAttribute("error", "查询用户异常");
		}

		request.setAttribute("user", user);

		//4、转向   转发到修改页面
		request.getRequestDispatcher("/jsp/user/user_update.jsp")
			.forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{

		//1、接收请求参数
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type");
		String idStr = request.getParameter("id");

		//2、校验参数
		//标识变量，标识是否有校验错误
		boolean hasError = false;

		if(name == null || "".equals(name)){
			hasError = true;
			request.setAttribute("nameError", "姓名不能为空");
		}
		else{
			if(name.length() > 20){
				hasError = true;
				request.setAttribute("nameError", "姓名不能超过20个字符");
			}
		}

		if(account == null || "".equals(account)){
			hasError = true;
			request.setAttribute("accountError", "账号不能为空");
		}

		if(pwd == null || "".equals(pwd)){
			hasError = true;
			request.setAttribute("pwdError", "密码不能为空");
		}

		if(type == null || "".equals(type)){
			hasError = true;
			request.setAttribute("typeError", "请选择用户类别");
		}

		int id = 0;

		if(idStr == null || "".equals(idStr)){
			hasError = true;
			request.setAttribute("error", "没有用户id,不可修改");
		}
		else{
			try{
				id = Integer.parseInt(idStr);
			}catch(Exception e){

			}
		}

		if(id == 0){
			hasError = true;
			request.setAttribute("error", "id值不合法");
		}

		User user = new User();
		user.setName(name);
		user.setAccount(account);
		user.setPwd(pwd);
		user.setType(type);
		user.setId(id);

		if(!hasError){
			//3、业务处理
			UserDao dao = new UserDao();
			try {
				dao.updateUser(user);
			} catch (SQLException e) {
				LOGGER.error("修改用户异常", e);
				hasError = true;
				request.setAttribute("error", "系统异常，请重试！");
			}
		}

		//4、根据处理的结果进行转向
		if(hasError){
			request.setAttribute("user", user);
			request.getRequestDispatcher("/jsp/user/user_update.jsp")
				.forward(request, response);
		}
		else{
			response.sendRedirect(request.getContextPath()
					+ "/user/query");
		}
	}
}
