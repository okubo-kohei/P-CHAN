package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDto;

public class MemberDao extends BaseDao {
	public String[] login(String loginId) {

		try {
			
			createConnection();
			
			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_member WHERE mail = ? OR tel = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, loginId);
			stmt.setString(2, loginId);

			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				String memberId = rs.getString("member_id");
				String pass = rs.getString("password");
				
				String[] list = {memberId, pass};
				
				return list;
			}
			
			return null;

		} catch (SQLException e) {

			System.out.println("ログイン処理に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("ログイン処理に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
	
	public String loginChange(String memberId) {

		try {
			
			createConnection();
			
			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_member WHERE member_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, memberId);

			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				String pass = rs.getString("password");
				
				return pass;
			}
			
			return null;

		} catch (SQLException e) {

			System.out.println("ログイン処理に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("ログイン処理に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
	
	public MemberDto searchDetail(String memberId) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_member WHERE member_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, memberId);

			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			MemberDto member = new MemberDto();
			member.setMemberId(rs.getString("member_id"));
			member.setMail(rs.getString("mail"));
			//PASSは未入手
			member.setName(rs.getString("name"));
			member.setPost(rs.getInt("post"));
			member.setPrefecture(rs.getString("prefecture"));
			member.setMunicipality(rs.getString("municipality"));
			member.setAddress(rs.getString("address"));
			member.setBuilding(rs.getString("building"));
			member.setTel(rs.getString("tel"));
			member.setCurrentPoint(rs.getInt("current_point"));
			member.setCumulativePoint(rs.getInt("cumulative_point"));
			member.setRank(rs.getInt("rank"));
			
			return member;

		} catch (SQLException e) {
			System.out.println("会員情報の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("会員情報の取得に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
	
	public int signUp(MemberDto member) {

		try {
			createConnection();
			
			String sql = "SELECT * FROM m_member WHERE mail = ?";
			
			System.out.println(sql);
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, member.getMail());
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			int row = rs.getRow();
			
			if(row != 0) {
				return 0;
			}
			
			// 実行するSQL文を作成します。
			sql = "INSERT INTO m_member (member_id,mail,password,name,post,prefecture,municipality," + 
					"address,building,tel,current_point,cumulative_point,rank) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMail());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getName());
			stmt.setInt(5, member.getPost());
			stmt.setString(6, member.getPrefecture());
			stmt.setString(7, member.getMunicipality());
			stmt.setString(8, member.getAddress());
			stmt.setString(9, member.getBuilding());
			stmt.setString(10, member.getTel());
			stmt.setInt(11, member.getCurrentPoint());
			stmt.setInt(12, member.getCumulativePoint());
			stmt.setInt(13, member.getRank());

			int r = stmt.executeUpdate();
			
			con.commit();
			return r;

		} catch (SQLException e) {
			System.out.println("登録に失敗しました。");
			System.out.println(e);
			return 0;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
				
			} catch (SQLException e) {
				System.out.println("登録に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
	
	public String memberIdGen() {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT member_id FROM m_member ORDER BY member_id";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			String memberId = "";
			while(rs.next()) {
				memberId = rs.getString("member_id");
			}
			return memberId;

		} catch (SQLException e) {
			
			System.out.println("行数の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			// データベースから切断します。
			try {
				closeConnection();
			} catch (SQLException e) {
				System.out.println("行数の取得に失敗しました。");
			}
			
		}

	}
	
	public int updatePass(String memberId, String newPass) {

		try {
			// データベースへ接続します。
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "UPDATE m_member SET password = ? WHERE member_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, newPass);
			stmt.setString(2, memberId);


			// SQLを実行します。
			int num = stmt.executeUpdate();
			
			con.commit();
			return num;

		} catch (SQLException e) {

			System.out.println("パスワードの更新に失敗しました。");
			System.out.println(e);
			return 0;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("パスワードの更新に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
	
	public int memberDel(String memberId) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "DELETE FROM m_member WHERE member_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, memberId);


			// SQLを実行します。
			int num = stmt.executeUpdate();
			
			con.commit();
			return num;

		} catch (SQLException e) {

			System.out.println("会員情報の削除に失敗しました。");
			System.out.println(e);
			return 0;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
					
			} catch (SQLException e) {

				System.out.println("会員情報の削除に失敗しました。");
				System.out.println(e);
				
			}
			
		}

	}
	
	public int updateMember(MemberDto member) {

		try {
			createConnection();

			// 実行するSQL文を作成します
			
			String sql = "UPDATE m_member SET mail = ?, name = ?, post = ?, prefecture = ?, municipality = ?," + 
					" address = ?, building = ?, tel = ?, current_point = ?, cumulative_point = ?, rank = ? WHERE member_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, member.getMail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getPost());
			stmt.setString(4, member.getPrefecture());
			stmt.setString(5, member.getMunicipality());
			stmt.setString(6, member.getAddress());
			stmt.setString(7, member.getBuilding());
			stmt.setString(8, member.getTel());
			stmt.setInt(9, member.getCurrentPoint());
			stmt.setInt(10, member.getCumulativePoint());
			stmt.setInt(11, member.getRank());
			stmt.setString(12, member.getMemberId());


			// SQLを実行します。
			int num = stmt.executeUpdate();
			
			con.commit();
			return num;

		} catch (SQLException e) {

			System.out.println("会員情報の更新に失敗しました。");
			System.out.println(e);
			return 0;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("会員情報の更新に失敗しました。");
				System.out.println(e);
			}
			
		}

	}
}
