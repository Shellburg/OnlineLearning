package cn.st.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {

	
	/**
	 * 静态资源只要静态方法才能使用
	 * @author 潘盛武
	 * @date 2017年11月27日22:59:48
	 */
	public static final String JDriver="com.mysql.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/db_online_learning?useUnicode=true&characterEncoding=UTF-8";
	public static final String user="root";
	public static final String password="";
	/**
	 * 获取数据库连接
	 * */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 动态导入数据库的驱动
			Class.forName(JDriver);
			// 获取数据库链接
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 * */
	public static void colse(Connection conn, PreparedStatement pstmt, 
			ResultSet rs, Statement stmt) {
		try {
			if (conn != null) { 
				conn.close();
			}
			if (pstmt != null) { 
				pstmt.close();
			}
			if (rs != null) { 
				rs.close();
			}
			if (stmt != null) { 
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 数据操作不成功则事务回滚
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				System.out.println("===rollback===");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
