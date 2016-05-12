package com.newer.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newer.util.Constants;
import com.newer.util.JDBC;

public class UserDao {

	public void saveUser(User user) throws SQLException{

		Connection conn = null;
		PreparedStatement pst = null;

		String sql = "insert into t_user(u_id,u_name,"
				+ "u_account,u_pwd,u_type,"
				+ "u_create_time,u_create_user)"
				+ " values(seq_user_id.nextval,?,?,"
				+ " ?,?,sysdate,?)";

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql);

			//设置参数值
			int i = 1;
			pst.setString(i++, user.getName());
			pst.setString(i++, user.getAccount());
			pst.setString(i++, user.getPwd());
			pst.setString(i++, user.getType());
			pst.setObject(i++, user.getCreateUser());

			//执行
			pst.executeUpdate();
		}finally{
			JDBC.colse(conn, pst, null);
		}
	}


	public List<User> queryUsers(String name,
			String account,String type) throws SQLException{

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.u_name create_user_name ");
		sql.append(" from t_user a,t_user b ");
		sql.append(" where a.u_create_user = b.u_id ");
		sql.append(" and a.is_valid = ? ");

		if(name != null && !"".equals(name)){
			sql.append(" and a.u_name like ? ");
		}

		if(account != null && !"".equals(account)){
			sql.append(" and a.u_account = ? ");
		}

		if(type != null && !"".equals(type)){
			sql.append(" and a.u_type = ? ");
		}

		List<User> list = null;

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql.toString());

			//设置参数
			int i = 1;
			pst.setString(i++, Constants.DATA_VALID);

			if(name != null && !"".equals(name)){
				pst.setString(i++, "%" + name + "%");
			}

			if(account != null && !"".equals(account)){
				pst.setString(i++, account);
			}

			if(type != null && !"".equals(type)){
				pst.setString(i++, type);
			}

			//执行
			rs = pst.executeQuery();

			list = new ArrayList<>();

			while(rs.next()){
				User user = new User();
				list.add(user);

				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setAccount(rs.getString("u_account"));
				user.setType(rs.getString("u_type"));
				user.setCreateUser(rs.getInt("u_create_user"));
				user.setCreateUserName(rs.getString("create_user_name"));
				user.setCreateDate(rs.getDate("u_create_time"));
			}
		}finally{
			JDBC.colse(conn, pst, rs);
		}

		return list;
	}



	public List<User> queryUsers(String name,String account) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();

		sql.append("select a.*,b.u_name create_user_name from t_user a,t_user b "
				+ "where a.u_create_user = b.u_id(+) "
				+ "and a.is_valid = "
				+ Constants.DATA_VALID);

		if(name != null && !"".equals(name)){
			sql.append(" and a.u_name like ? ");
		}

		if(account != null && !"".equals(account)){
			sql.append(" and a.u_account like ? ");
		}

		List<User> list = new ArrayList<>();

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql.toString());

			//设置参数
			int i = 1;
			if(name != null && !"".equals(name)){
				pst.setString(i++, "%" + name + "%");
			}

			if(account != null && !"".equals(account)){
				pst.setString(i++, "%" + account + "%");
			}

			//执行
			rs = pst.executeQuery();

			while(rs.next()){
				User user = new User();
				list.add(user);

				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setAccount(rs.getString("u_account"));
				user.setPwd(rs.getString("u_pwd"));
				user.setType(rs.getString("u_type"));
				user.setCreateDate(rs.getDate("u_create_time"));
				user.setCreateUser(rs.getInt("u_create_user"));
				user.setCreateUserName(rs.getString("create_user_name"));
			}
		}finally{
			JDBC.colse(conn, pst, rs);
		}

		return list;

	}

	public User getUserByAccount(String account) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select * from t_user "
				+ " where is_valid = ? "
				+ " and u_account = ? ";

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, Constants.DATA_VALID);
			pst.setString(2, account);

			rs = pst.executeQuery();

			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setAccount(rs.getString("u_account"));
				user.setPwd(rs.getString("u_pwd"));
				user.setType(rs.getString("u_type"));
				user.setCreateDate(rs.getDate("u_create_time"));
				user.setCreateUser(rs.getInt("u_create_user"));

				return user;
			}
		}finally{
			JDBC.colse(conn, pst, rs);
		}

		return null;
	}


	/**
	 * 根据id获得用户信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User getUserById(int id) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select * from t_user "
				+ " where is_valid = ? "
				+ " and u_id = ? ";

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, Constants.DATA_VALID);
			pst.setInt(2, id);

			rs = pst.executeQuery();

			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setAccount(rs.getString("u_account"));
				user.setPwd(rs.getString("u_pwd"));
				user.setType(rs.getString("u_type"));
				user.setCreateDate(rs.getDate("u_create_time"));
				user.setCreateUser(rs.getInt("u_create_user"));

				return user;
			}
		}finally{
			JDBC.colse(conn, pst, rs);
		}

		return null;
	}

	public void updateUser(User user) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;

		String sql = "update t_user"
				+ " set u_name = ?, "
				+ " u_account = ?, "
				+ " u_pwd = ?, "
				+ " u_type = ? "
				+ " where u_id = ? ";

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql);

			//设置参数值
			int i = 1;
			pst.setString(i++, user.getName());
			pst.setString(i++, user.getAccount());
			pst.setString(i++, user.getPwd());
			pst.setString(i++, user.getType());
			pst.setInt(i++, user.getId());

			//执行
			pst.executeUpdate();
		}finally{
			JDBC.colse(conn, pst, null);
		}
	}

	public void deleteUser(int id) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;

		String sql = "update t_user"
				+ " set is_valid = ? "
				+ " where u_id = ? ";

		try{
			conn = JDBC.getConnection();
			pst = conn.prepareStatement(sql);

			//设置参数值
			int i = 1;
			pst.setString(i++, Constants.DATA_INVALID);
			pst.setInt(i++, id);

			//执行
			pst.executeUpdate();
		}finally{
			JDBC.colse(conn, pst, null);
		}
	}
}
