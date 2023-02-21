package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.BuyProductDto;
import dto.HistoryDto;

public class HistoryDao extends BaseDao {
	public ArrayList<HistoryDto> searchHistory(String memberId) {

		try {
			createConnection();
			
			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM t_voucher WHERE member_id = ?";
	
			System.out.println(sql);
	
			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);
	
			stmt.setString(1, memberId);
			
			ResultSet rs = stmt.executeQuery();
	
			ArrayList<HistoryDto> historyList = new ArrayList<HistoryDto>();
			
			while(rs.next()) {
				HistoryDto hsDao = new HistoryDto();
				
				hsDao.setMemberId(memberId);
				hsDao.setProductId(rs.getString("product_id"));
				hsDao.setQuantity(rs.getInt("quantity"));
				
				historyList.add(hsDao);
			}
			
			return historyList;			

		} catch (SQLException e) {
			System.out.println("購入履歴の取得に失敗しました。");
			System.out.println(e);
			return null;
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("購入履歴の取得に失敗しました。");
				System.out.println(e);
			}
		}
	}
	
	public boolean addHistory(ArrayList<BuyProductDto> buyList, String memberId,Timestamp timestamp) {

		try {
			createConnection();
			
			int loop = buyList.size();
			
			for(int i = 0; i < loop; i++) {
				// 実行するSQL文を作成します。
				String sql = "INSERT INTO t_voucher (member_id,product_id,quantity,timestamp) VALUES (?,?,?,?)";
		
				System.out.println(sql);
				
				BuyProductDto buy = buyList.get(i);
				String productId = buy.getProductId();
				int quantity = buy.getQuantity();
				
				// SQLを実行するためのステートメントを取得します。
				stmt = con.prepareStatement(sql);
		
				stmt.setString(1, memberId);
				stmt.setString(2, productId);
				stmt.setInt(3, quantity);
				stmt.setTimestamp(4, timestamp);
				
				int num = stmt.executeUpdate();
		
				if(num != 1) {
					return false;
				}

			}
			
			con.commit();			
			return true;			

		} catch (SQLException e) {
			System.out.println("購入履歴の更新に失敗しました。");
			System.out.println(e);
			return false;
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("購入履歴の更新に失敗しました。");
				System.out.println(e);
			}
		}
	}
	
}
