package com.newer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newer.util.Constants;

public class LoginFilter implements Filter {

	private String loginPath;
	private String loginServlet;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//如何判断用户是否登录？
		//检查session中是否有 curr_user
		HttpServletRequest request = (HttpServletRequest) arg0;

		//判断当前请求的地址是否与登录界面地址相同
		//1、取到当前请求地址
		String currPath = request.getRequestURI();

		if(!currPath.equals(request.getContextPath() + this.loginPath)
			&&  !currPath.equals(request.getContextPath() + this.loginServlet)
				&&
			request.getSession().getAttribute(Constants.CURR_USER) == null){
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.sendRedirect(request.getContextPath() + "/jsp/login/login.jsp");
		}
		else{
			//已登录或不需要登录，就转到链上的下一个进行过滤处理
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		this.loginPath = config.getInitParameter("login-url");
		this.loginServlet = config.getInitParameter("login-servlet");
	}

}
