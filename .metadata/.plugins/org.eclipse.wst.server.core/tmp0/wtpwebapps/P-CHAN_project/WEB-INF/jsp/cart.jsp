<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.BuyProductDto"%>
<%@page import="dao.ProductDao"%>
<%@page import="dto.ProductDto"%>
<!DOCTYPE html>
<!--3カート-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan</title>
<link rel="stylesheet" href="css/cart.css">
<link rel="stylesheet" href="css/style.css">
<%
HttpSession ses = request.getSession();
Cookie cookie[] = request.getCookies();
ArrayList<BuyProductDto> buyList = (ArrayList<BuyProductDto>) ses.getAttribute("CART");
String judge = null;
if (cookie != null) {
	for (int i = 0; i < cookie.length; i++) {
		if (cookie[i].getName().equals("memberId")) {
	judge = cookie[i].getValue();
		}
	}
}
%>
</head>
<body>
	<header>
		<nav id="global_navi">
			<form action="FromHeaderServlet" method="post">
				<ul>
					<!--/商品検索機能-->
					<li id="searsub"><input type="search" id="search"
						name="searchWords" placeholder="キーワードを入力">
						<button type="submit" id="submit" value="search" name="flg">検索</button>
					</li>
					<!--/会員情報表示-->
					<li>
						<%
						if (judge != null) {
						%>
						<div class="profile icon"></div>
						<button type="submit" value="member" name="flg">会員情報</button> <%
 } else {
 %>
						<div class="profile icon"></div>
						<button type="submit" value="login" name="flg">会員情報</button> <%
 }
 %>
					</li>
					<!--/カート画面-->
					<li>
						<%
						if (judge != null) {
						%>
						<div class="cart icon"></div> <i class="gg-shopping-cart"></i>
						<button type="submit" value="cart" name="flg">カート</button> <%
 } else {
 %>
						<div class="cart icon"></div> <i class="gg-shopping-cart"></i>
						<button type="submit" value="login" name="flg">カート</button> <%
 }
 %>
					</li>
					<li>
						<!--/ログイン画面-->
						<div id="login">
							<%
							if (judge != null) {
							%>
							<input type="hidden" name="beforeLogout" value="cart.jsp">
							<div class="key icon"></div>
							<button type="submit" value="logout" name="flg">ログアウト</button>
							<%
							} else {
							%>
							<div class="key icon"></div>
							<button type="submit" value="login" name="flg">ログイン</button>
							<%
							}
							%>
						</div>
					</li>
				</ul>
			</form>
		</nav>
	</header>

	<div id="left">
		<form action="MovingIndex">
			<input type="submit" value="一覧へ戻る" id="index_url">
		</form>
		<div id="cart_index">
			<p id="cart_txt">カート</p>
			<%
			int total1 = 0;
			int total2 = 0;
			if (buyList == null) {
			%>
			<p id="no_product">カートに商品がありません</p>
			<%
			} else {
			for (int i = 0; i < buyList.size(); i++) {
				BuyProductDto allGoods = (BuyProductDto) buyList.get(i);
			%>
			<div id="product">
				<div class="products" id="<%=allGoods.getProductId()%>">
					<img src="images/<%=allGoods.getProductId()%>.jpg" alt="商品画像">
					<div id="product_txt">
						<p>
							商品名:<%=allGoods.getProductName()%></p>
						<p>
							単体価格:<%=allGoods.getPrice()%>円
						</p>
						<p>
							購入数:<%=allGoods.getQuantity()%></p>
						<%
						total1 += allGoods.getQuantity();
						total2 += allGoods.getPrice() * allGoods.getQuantity();
						%>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>
	<div id="right">
		<section id="buy">
			<p><%=total1%>個の商品
			</p>
			<p>
				小計 <span id="price"> <%=total2%>円
				</span> <span id="tax">(税込)</span>
			</p>
			<%
			if (judge != null && buyList != null) {
			%>
			<form action="ToBuyServlet" method="post">
				<input type="hidden" name="flg" value="cart">
				<button type="submit" id="buy_url">購 入</button>
			</form>
			<%
			} else if (judge == null && buyList != null) {
			%>
			<form action="FromHeaderServlet" method="post">
				<input type="hidden" name="flg" value="login">
				<button type="submit" id="buy_url">購 入</button>
			</form>
			<%
			}
			%>
		</section>
		<section id="recommend">
			<p>おすすめの商品</p>
			<%
			if (buyList != null) {
				for (int i = 0; i < buyList.size(); i++) {
					BuyProductDto allGoods = (BuyProductDto) buyList.get(i);
					String rec = allGoods.getProductId();
					int rec2 = Integer.parseInt(rec.substring(4, 5));
					char rec3;
					if (rec2 != 4) {
				rec2 = rec2 + 1;
				rec3 = Character.forDigit(rec2, 10);
					} else {
				rec2 = rec2 - 3;
				rec3 = Character.forDigit(rec2, 10);
					}
					StringBuilder sb = new StringBuilder(rec);
					sb.setCharAt(4, rec3);
					rec = sb.toString();
					ProductDao pDao = new ProductDao();
					ProductDto pDto = new ProductDto();
					pDto = pDao.searchDetail(rec);
			%>
			<div id="product">
				<div class="products" id="<%=pDto.getProductId()%>">
					<img src="images/<%=rec%>.jpg" alt="商品画像">
					<div id="product_txt">
						<p>
							商品名:<%=pDto.getProductName()%></p>
						<p>
							単体価格:<%=pDto.getPrice()%>円
						</p>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</section>
	</div>


	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/img.js"></script>
</body>
</html>