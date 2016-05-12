package com.newer.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class BookTypeQueryServlet
 */
public class BookTypeQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(BookTypeQueryServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookTypeQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取请求参数
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		//调用dao的查询方法，取得图书列表的list
		BookTypeDao dao = new BookTypeDao();
		try {
			List<BookType> list = dao.query(name);
			request.setAttribute("list", list);
		} catch (SQLException e) {
			LOGGER.error("查询图书类别异常", e);
			request.setAttribute("message", "系统异常，请重试！");
		}

		request.getRequestDispatcher("/jsp/book/book_type_query.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
