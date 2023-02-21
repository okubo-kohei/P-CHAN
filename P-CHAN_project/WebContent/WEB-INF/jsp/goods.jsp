<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProductDao"%>
<%@page import="dto.ProductDto"%>
<%@page import="dto.BuyProductDto"%>
<!DOCTYPE html>
<!--17 商品画面-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/goods.css">
</head>
<body>
<%
 Cookie cookie[] = request.getCookies();
 String judge = null;
 if (cookie != null) {
 	for (int i = 0; i < cookie.length; i++) {
 		if (cookie[i].getName().equals("memberId")) {
 	judge = cookie[i].getValue();
 		}
 	}
 }
 %>

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
					<div class="cart icon"></div>
					<i class="gg-shopping-cart"></i>
				<button type="submit" value="cart" name="flg">カート</button> <%
 					} else {
 %>
					<div class="cart icon"></div>
					<i class="gg-shopping-cart"></i>
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
						<input type="hidden" name="beforeLogout" value="index.jsp">
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

	<%
	ProductDto product = new ProductDto();
	HttpSession ses = request.getSession();
	product = (ProductDto) ses.getAttribute("PRODUCT");
	ses.setAttribute("FROMPRODUCT", product);
	%>
	<aside id="main">
		<img src="images/<%=product.getProductId()%>.jpg" alt="商品画像"
			class="image">

		<aside id="btn">
			<h1>
				購入数 <select name="quantity" id="quantity" tabindex="0"
					data-action="a-dropdown-select"
					class="a-native-dropdown a-declarative" form="MovingBuy">

					<%
					for (int i = 1; i < 100; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
					}
					%>
				</select>
			</h1>
			<%
		if (judge != null) {
		%>
			<form action="AddCartServlet" method="post" id="AddCartServlet">
				<input type="hidden" id="formQuantity" name="formQuantity" value="">
				<button type="submit" name="productId" id="productId"
					value="<%=product.getProductId()%>" class="btn btn-cart">カートへ入れる</button>
			</form>
		<%
		} else {
		%>
			<form action="FromHeaderServlet" method="post">
			<input type="hidden" name="flg" value="login">
			<button type="submit" name="productId" id="productId"
					value="<%=product.getProductId()%>" class="btn btn-cart">カートへ入れる</button>
			</form>
		<%
		}
		%>
		</aside>
		<form action="MovingIndex">
			<button type="submit" class="btn btn-index">　　 一 覧 　　</button>
			<br>
		</form>


		<%
		if (judge != null) {
		%>
		<form method="post" action="ToBuyServlet" id="MovingBuy">
			<input type="hidden" name="ProductId"
				value="<%=product.getProductId()%>"> <input type="hidden"
				name="flg" value="product">
			<button type="submit"class="btn btn-buy">　　 購 入 　　</button>
		</form>
		<%
		} else {
		%>
		<form action="FromHeaderServlet" method="post">
			<input type="hidden" name="flg" value="login">
			<button type="submit" class="btn btn-buy">　　 購 入 　　</button>
		</form>
		<%
		}
		%>
	</aside>


	<aside id="sidebar">

		<h1>
			<p class="product_name"><%=product.getProductName()%></p>
			<br>
			<p><%=product.getPrice()%>
				円 在庫数
				<%=product.getStock()%>
			</p>
		</h1>
		<br>

		<h1>商品説明</h1>
		<div class="salespoint">
			<p>
				<%=product.getSalesPoint()%>
			</p>
		</div>
	</aside>

	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/formQuantity.js"></script>
</body>
</html>