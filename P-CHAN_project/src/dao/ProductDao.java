package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BuyProductDto;
import dto.ProductDto;

public class ProductDao extends BaseDao {
	public ArrayList<ProductDto> searchProduct(String word) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product WHERE product_name LIKE ? OR maker LIKE ? OR price LIKE ? OR type LIKE ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1,'%' + word + '%');
			stmt.setString(2,'%' + word + '%');
			stmt.setString(3,'%' + word + '%');
			stmt.setString(4,'%' + word + '%');

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> goodsList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				goodsList.add(goods);
			}
			
			con.commit();
			return goodsList;

		} catch (SQLException e) {
			
			System.out.println("商品検索に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
					
			} catch (SQLException e) {

				System.out.println("商品検索に失敗しました。");
				System.out.println(e);
					
			}
			
		}

	}
	
	public ProductDto searchDetail(String productId) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product WHERE product_id = ?";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1, productId);

			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			ProductDto goods = new ProductDto();
			goods.setProductId(rs.getString("product_id"));
			goods.setProductName(rs.getString("product_name"));
			goods.setPrice(rs.getInt("price"));
			goods.setStock(rs.getInt("stock"));
			goods.setMaker(rs.getString("maker"));
			goods.setType(rs.getString("type"));
			goods.setSalesPoint(rs.getString("sales_point"));
			
			con.commit();
			return goods;

		} catch (SQLException e) {

			System.out.println("商品詳細の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("商品詳細の取得に失敗しました。");
				System.out.println(e);
				
			}
			
		}

	}
	
	public ArrayList<ProductDto> allProduct() {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> goodsList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				goodsList.add(goods);
			}
			
			con.commit();
			return goodsList;

		} catch (SQLException e) {

			System.out.println("商品情報の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("商品情報の取得に失敗しました。");
				System.out.println(e);
				
			}
			
		}

	}
	
	public boolean buy(ArrayList<BuyProductDto> buyList) {
			
			try {
				createConnection();
				
				int size = buyList.size();
				boolean check = true;
				
				for(int i = 0; i < size; i++) {
					BuyProductDto buyProduct = new BuyProductDto();
					buyProduct = buyList.get(i);
					
					String productId = buyProduct.getProductId();
					int buys = buyProduct.getQuantity();
					
					String sql = "SELECT stock FROM m_product WHERE product_id = ? for update";
					
					stmt = con.prepareStatement(sql);
					stmt.setString(1, productId);
					
					ResultSet rs = stmt.executeQuery();
					
					rs.next();
					int stock = rs.getInt("stock");
					
					// 在庫が足りない場合は更新処理を行わない。
					if(stock < buys) {
						return false;
					}
					
					sql = "UPDATE m_product SET stock = ? WHERE product_id = ?";
					stmt = con.prepareStatement(sql);
	
					int sub = stock - buys;
					stmt.setInt(1, sub);
					stmt.setString(2, productId);
					
					stmt.executeUpdate();
					
				}
				
				con.commit();
				return true;
				
			} catch(Exception e) {
				
				System.out.println("購入に失敗しました。");
				System.out.println(e);
				return false;
				
			} finally {
				try {
					
					closeConnection();
					
				} catch(Exception e) {
					
					System.out.println("購入に失敗しました。");
					System.out.println(e);
					
				}
			}
		}
	public ArrayList<ProductDto> nameSort() {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product ORDER BY product_name";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> nameList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				nameList.add(goods);
			}
			con.commit();
			return nameList;

		} catch (SQLException e) {

			System.out.println("商品情報の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("商品情報の取得に失敗しました。");
				System.out.println(e);
				
			}
			
		}

	}
	public ArrayList<ProductDto> makerSort() {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product ORDER BY maker";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> makerList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				makerList.add(goods);
			}
			con.commit();
			return makerList;

		} catch (SQLException e) {

			System.out.println("商品情報の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("商品情報の取得に失敗しました。");
				System.out.println(e);
				
			}
			
		}

	}
	public ArrayList<ProductDto> priceSort() {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product ORDER BY price";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> priceList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				priceList.add(goods);
			}
			con.commit();
			return  priceList;

		} catch (SQLException e) {

			System.out.println("商品情報の取得に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			try {

				closeConnection();
				
			} catch (SQLException e) {

				System.out.println("商品情報の取得に失敗しました。");
				System.out.println(e);
				
			}
			
		}
	}
	public ArrayList<ProductDto> searchSortName(String word) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product WHERE product_name LIKE ? OR maker LIKE ? OR price LIKE ? OR type LIKE ? ORDER BY product_name";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1,'%' + word + '%');
			stmt.setString(2,'%' + word + '%');
			stmt.setString(3,'%' + word + '%');
			stmt.setString(4,'%' + word + '%');

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> goodsList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				goodsList.add(goods);
			}
			
			con.commit();
			return goodsList;

		} catch (SQLException e) {
			
			System.out.println("商品検索に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
					
			} catch (SQLException e) {

				System.out.println("商品検索に失敗しました。");
				System.out.println(e);
					
			}
			
		}

	}
	public ArrayList<ProductDto> searchSortMaker(String word) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product WHERE product_name LIKE ? OR maker LIKE ? OR price LIKE ? OR type LIKE ? ORDER BY maker";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1,'%' + word + '%');
			stmt.setString(2,'%' + word + '%');
			stmt.setString(3,'%' + word + '%');
			stmt.setString(4,'%' + word + '%');

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> goodsList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				goodsList.add(goods);
			}
			
			con.commit();
			return goodsList;

		} catch (SQLException e) {
			
			System.out.println("商品検索に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
					
			} catch (SQLException e) {

				System.out.println("商品検索に失敗しました。");
				System.out.println(e);
					
			}
			
		}

	}
	public ArrayList<ProductDto> searchSortprice(String word) {

		try {
			createConnection();

			// 実行するSQL文を作成します。
			String sql = "SELECT * FROM m_product WHERE product_name LIKE ? OR maker LIKE ? OR price LIKE ? OR type LIKE ? ORDER BY price";

			System.out.println(sql);

			// SQLを実行するためのステートメントを取得します。
			stmt = con.prepareStatement(sql);

			stmt.setString(1,'%' + word + '%');
			stmt.setString(2,'%' + word + '%');
			stmt.setString(3,'%' + word + '%');
			stmt.setString(4,'%' + word + '%');

			ResultSet rs = stmt.executeQuery();
			
			ArrayList<ProductDto> goodsList = new ArrayList<ProductDto>();
			
			while(rs.next()) {
				ProductDto goods = new ProductDto();
				goods.setProductId(rs.getString("product_id"));
				goods.setProductName(rs.getString("product_name"));
				goods.setPrice(rs.getInt("price"));
				goods.setStock(rs.getInt("stock"));
				goods.setMaker(rs.getString("maker"));
				goods.setType(rs.getString("type"));
				goods.setSalesPoint(rs.getString("sales_point"));
				
				goodsList.add(goods);
			}
			
			con.commit();
			return goodsList;

		} catch (SQLException e) {
			
			System.out.println("商品検索に失敗しました。");
			System.out.println(e);
			return null;

		} finally {
			// データベースから切断します。
			try {

				closeConnection();
					
			} catch (SQLException e) {

				System.out.println("商品検索に失敗しました。");
				System.out.println(e);
					
			}
			
		}

	}
	
}