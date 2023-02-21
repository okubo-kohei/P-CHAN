package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {

	protected Connection con = null;
	protected PreparedStatement stmt = null;
	protected ResultSet rs = null; 

	protected void createConnection() throws SQLException {
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
			}
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/p_chan_db",
					"root",
					"");
			
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			
			System.out.println("DBの接続に失敗しました。");
			throw e;
			
		}
	}
	
	protected void closeConnection() throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("DBの切断に失敗しました。");
			throw e;
		}
	}
	

}
