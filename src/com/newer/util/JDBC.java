package com.newer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	static{
		//第1步：注册驱动（只做一次）
		try {
			// Class.formName("驱动程序的类名");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//建一个公用的方法来负责建立连接
	public static Connection getConnection() throws SQLException{
		//第2步：建立连接
		//DriverManager.getConnection(url, user, password)
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl",
				"scott",
				"tiger");
		return conn;
	}

	/**
	 * 通用的jdbc释放资源的方法
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void colse(Connection conn,
			Statement st,ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
