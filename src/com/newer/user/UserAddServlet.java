package com.newer.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newer.util.Constants;

public class UserAddServlet extends HttpServlet {

	private Logger LOGGER = LoggerFactory.getLogger(UserAddServlet.class);

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{

		//1、接收请求参数
		//request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type");

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

		//参数都合法
		if(!hasError){
		//3、逻辑处理
			//调用dao方法新增用户
			UserDao dao = new UserDao();
			User user = new User();
			user.setName(name);
			user.setAccount(account);
			user.setPwd(pwd);
			user.setType(type);

			//从session中获得当前登录用户，他就是创建人
			User currUser = (User) request.getSession()
					.getAttribute(Constants.CURR_USER);
			if(currUser != null){
				user.setCreateUser(currUser.getId());
			}

			try {
				//保存
				dao.saveUser(user);
			} catch (SQLException e) {
				hasError = true;
				request.setAttribute("error", "系统异常，请重试！");
				//一定要记得记录异常日志
				LOGGER.error("保存用户异常", e);
			}
		}

		//4、根据处理的结果进行转向
		if(hasError){
			request.getRequestDispatcher("/jsp/user/user_add.jsp")
				.forward(request, response);
		}
		else{
			response.sendRedirect(request.getContextPath()
					+ "/user/query");
		}
	}
}
