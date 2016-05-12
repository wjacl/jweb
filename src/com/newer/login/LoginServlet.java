package com.newer.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newer.user.User;
import com.newer.user.UserDao;
import com.newer.util.Constants;

public class LoginServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -4422451801601098357L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

	@Override   //重写
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		//获得请求参数
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		//返回的信息
		String message = null;

		//校验请求参数是否有效
		if(username == null || "".equals(username)
				|| pwd == null || "".equals(pwd)){
			message = "请填写用户名和密码";
		}
		else{
			//业务逻辑处理：判断用户名、密码是否正确
			//根据用户名到数据库中查询是否有该用户
			UserDao dao = new UserDao();
			User user = null;
			try {
				user = dao.getUserByAccount(username);
			} catch (SQLException e) {
				LOGGER.error("查询用户异常", e);
				message="系统异常，请重试！";
			}
			//有该用户，就继续判断密码是否正确
			if(user != null){
				if(pwd.equals(user.getPwd())){
					//用户密码正确.
					HttpSession session = request.getSession();
					session.setAttribute(Constants.CURR_USER, user);
				}
				else{
					message = "用户名或密码错误";
				}
			}else{
				//没有该用户，回到登录页，提示：用户名或密码错误
				if(message == null){
					message = "用户名或密码错误";
				}
			}
		}

		//根据业务逻辑处理的结果来选择转向到哪里
		if(message == null){
			//登录成功
			//重定向到菜单页
			response.sendRedirect("jsp/login/menu.jsp");
		}
		else{
			//登录失败
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/login/login.jsp")
				.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
}
