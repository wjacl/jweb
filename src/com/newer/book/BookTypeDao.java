package com.newer.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.util.Constants;
import com.newer.util.JDBC;

public class BookTypeDao {

	/**
	 * 新增图书类别到数据库中
	 * @param bt
	 * @throws SQLException
	 */
	public void saveBookType(BookType bt) throws SQLException{

		//入参进行检查
		if(bt == null){
			return;
		}

		Connection conn = null;
		PreparedStatement pst = null;

		String sql = "insert into t_book_type(bt_id,bt_name,"
				+ " bt_create_user,bt_create_time) "
				+ " values(seq_book_type_id.nextval,?,?,sysdate)";

		try{
			conn = JDBC.getConnection();

			pst = conn.prepareStatement(sql);

			pst.setString(1, bt.getName());
			pst.setInt(2, bt.getCreateUserId());

			pst.executeUpdate();

		}finally{
			JDBC.colse(conn, pst, null);
		}
	}


	public List<BookType> query(String name) throws SQLException{

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.u_name "
				+ " from t_book_type a,t_user b "
				+ " where a.bt_create_user = b.u_id "
				+ " and a.is_valid = ? ");
		if(name != null && !"".equals(name)){
			sql.append(" and a.bt_name like ? ");
		}

		List<BookType> list = null;

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, Constants.DATA_VALID);
			if(name != null && !"".equals(name)){
				pst.setString(2, "%" + name + "%");
			}

			rs = pst.executeQuery();

			list = new ArrayList<BookType>();
			while(rs.next()){
				BookType bt = new BookType();
				list.add(bt);

				bt.setId(rs.getInt("bt_id"));
				bt.setName(rs.getString("bt_name"));
				bt.setCreateUserId(rs.getInt("bt_create_user"));
				bt.setCreateUserName(rs.getString("u_name"));
				bt.setCreateTime(rs.getDate("bt_create_time"));
			}

		}finally{
			JDBC.colse(conn, pst, rs);
		}

		return list;
	}
}
