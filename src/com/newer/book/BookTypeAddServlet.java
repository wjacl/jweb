package com.newer.book;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newer.user.User;
import com.newer.util.Constants;

public class BookTypeAddServlet extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = -220418684647204328L;

	private static final Logger LOGGER = LoggerFactory.getLogger(BookTypeAddServlet.class);

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{

		//接收请求参数
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		String message = null;

		//校验输入是否合法
		if(name == null || "".equals(name)){
			message = "请填写类别名称！";
		}
		else{
			if(name.length() > 10){
				message = "类别名称不能超过10个字符！";
			}
		}

		//参数合法，则保持到数据库中
		if(message == null){
			BookType bt = new BookType();
			bt.setName(name);

			User user = (User) request.getSession().getAttribute(Constants.CURR_USER);

			if(user == null){
				//说明没登录
				response.sendRedirect(request.getContextPath() + "/jsp/login/login.jsp");
				return;
			}

			bt.setCreateUserId(user.getId());

			BookTypeDao dao = new BookTypeDao();
			try {
				dao.saveBookType(bt);
			} catch (SQLException e) {
				LOGGER.error("保存图书类别失败", e);
				message = "系统异常，请重试！";
			}
		}

		//根据处理结果进行转向
		if(message == null){
			//处理成功，转到查询列表页面
			response.sendRedirect(request.getContextPath() + "/book/type/query");
		}
		else{
			//失败了，回到新增页面
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/book/book_type_add.jsp")
				.forward(request, response);
		}
	}

}
