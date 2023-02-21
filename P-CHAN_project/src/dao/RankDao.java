package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDao extends BaseDao {
	
	public int searchRank(int point) {
		
		try {
			
			createConnection();
			
			String sql = "SELECT rank FROM t_rank WHERE point >= ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, point);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			int rank = rs.getInt("rank");
			
			con.commit();
			return rank;
			
		} catch (SQLException e) {
			
			System.out.println("ランク検索に失敗しました。");
			System.out.println(e);
			return 0;
			
		} finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("ランク検索に失敗しました。");
				System.out.println(e);
			}
			
		}
		
	}
	
public int searchDiscount(int rank) {
		
		try {
			
			createConnection();
			
			String sql = "SELECT discount FROM t_rank WHERE rank = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, rank);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			int discount = rs.getInt("discount");
			
			con.commit();
			return discount;
			
		} catch (SQLException e) {
			
			System.out.println("割引検索に失敗しました。");
			System.out.println(e);
			return 0;
			
		} finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("割引検索に失敗しました。");
				System.out.println(e);
			}
			
		}
		
	}

}
