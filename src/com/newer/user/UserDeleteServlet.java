package com.newer.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class UserDeleteServlet
 */
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = LoggerFactory.getLogger(UserDeleteServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

		//3、业务处理
		//查询出指定id 的用户
		if(id != 0){
			UserDao dao = new UserDao();
			try {
				dao.deleteUser(id);
			} catch (SQLException e) {
				LOGGER.error("删除用户异常", e);
				request.setAttribute("error", "系统异常，删除失败!");
				request.getRequestDispatcher("/user/query")
					.forward(request, response);
				return;
			}
		}

		//4、重定到查询
		response.sendRedirect(
				request.getContextPath()
				+ "/user/query");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
