package com.newer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String  charSet = "UTF-8";

	@Override
	public void destroy() {

		System.out.println("编码过滤器被销毁了");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//进行编码设置
		request.setCharacterEncoding(this.charSet);
		response.setCharacterEncoding(this.charSet);

		//转到链上的下一个
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("编码过滤器被初始化了");
		String initCharSet = config.getInitParameter("charSet");
		if(initCharSet != null){
			this.charSet = initCharSet;
		}
	}

}
