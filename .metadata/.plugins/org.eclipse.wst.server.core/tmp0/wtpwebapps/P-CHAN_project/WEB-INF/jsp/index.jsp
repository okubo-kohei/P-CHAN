<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="dao.ProductDao"%>
<%@page import="dto.ProductDto"%>
<!DOCTYPE html>
<!--2 商品一覧-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<%
HttpSession ses = request.getSession();
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
	<div class="main_header">
		<select name="sources" id="sources" class="custom-select sources"
			placeholder="ソート">
			<option value="name">五十音順</option>
			<option value="maker">メーカー名</option>
			<option value="price">価格昇順</option>
		</select>
	
	<div class="customer_btn">
		<a href="MovingContact">カスタマーサポート</a>
		<!--16 カスタマーサポート-->
	</div>
	</div>
	<%
	ArrayList<ProductDto> allList = (ArrayList<ProductDto>) ses.getAttribute("ALLLIST");
	ArrayList<ProductDto> productList = (ArrayList<ProductDto>) ses.getAttribute("PRODUCTLIST");
	ArrayList<ProductDto> nameList = (ArrayList<ProductDto>) ses.getAttribute("NAMELIST");
	ArrayList<ProductDto> makerList = (ArrayList<ProductDto>) ses.getAttribute("MAKERLIST");
	ArrayList<ProductDto> priceList = (ArrayList<ProductDto>) ses.getAttribute("PRICELIST");
	
	if (productList != null && productList.size() > 0) {
	%>
	<main>
	<!-- 検索後 初期表示-->
		<section id="initial">
			<table>
				<%
				int i = 0;
				int count = 1;
					for(int x = 0;x < productList.size() / 4 || productList.size() < 4;x++){
				%>
						<tr>
				<%
						for (;i < productList.size(); i++) {
								ProductDto goods = (ProductDto) productList.get(i);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i == (4 * count) - 1){
							count += 1;
							i++;
							break;
						}
						}
						%>
						</tr>
					<% 
						if(productList.size() < 4 && x == 4){
							break;
						}
					}
					%>
			</table>
		</section>
	<!-- 検索後 五十音-->	
		<section id="name">
			<table>
				<%
				int i1 = 0;
				int count1 = 1;
					for(int x = 0;x < nameList.size() / 4 || nameList.size() < 4;x++){
				%>
						<tr>
				<%
						for (;i1 < nameList.size(); i1++) {
								ProductDto goods = (ProductDto) nameList.get(i1);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i1 == (4 * count1) - 1){
							count1 += 1;
							i1++;
							break;
						}
						}
						%>
						</tr>
					<% 
					if(productList.size() < 4 && x == 4){
						break;
					}
					}
					%>
			</table>
		</section>
	<!-- 検索後 メーカー順-->	
		<section id="maker">
			<table>
				<%
				int i2 = 0;
				int count2 = 1;
					for(int x = 0;x < makerList.size() / 4 || makerList.size() < 4;x++){
				%>
						<tr>
				<%
						for (;i2 < makerList.size(); i2++) {
								ProductDto goods = (ProductDto) makerList.get(i2);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i2 == (4 * count2) - 1){
							count2 += 1;
							i2++;
							break;
						}
						}
						%>
						</tr>
					<% 
					if(productList.size() < 4 && x == 4){
						break;
					}
					}
					%>
			</table>
		</section>
	<!-- 検索後 値段順-->
		<section id="price">
			<table>
				<%
				int i3 = 0;
				int count3 = 1;
					for(int x = 0;x < priceList.size() / 4 || priceList.size() < 4;x++){
				%>
						<tr>
				<%
						for (;i3 < priceList.size(); i3++) {
								ProductDto goods = (ProductDto) priceList.get(i3);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i3 == (4 * count3) - 1){
							count3 += 1;
							i3++;
							break;
						}
						}
						%>
						</tr>
					<% 
					if(productList.size() < 4 && x == 4){
						break;
					}
					}
					%>
			</table>
		</section>
	</main>
	<%
	} else {
	%>
	<main>
	<!-- 検索前 初期表示 -->
		<section id= "initial">
			<table>
				<%
				int i = 0;
				int count = 1;
					for(int x = 0;x < allList.size() / 4;x++){
				%>
					<tr>
				<%
						for (;i < allList.size(); i++) {
								ProductDto goods = (ProductDto) allList.get(i);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i == (4 * count) - 1){
							count += 1;
							i++;
							break;
						}
						}
						%>
					</tr>
					<% 
					}
					%>
			</table>
		</section>
	<!-- 検索前 五十音-->	
		<section id="name">
			<table>
				<%
				int i1 = 0;
				int count1 = 1;
					for(int x = 0;x < nameList.size() / 4;x++){
				%>
						<tr>
				<%
						for (;i1 < nameList.size(); i1++) {
								ProductDto goods = (ProductDto) nameList.get(i1);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i1 == (4 * count1) - 1){
							count1 += 1;
							i1++;
							break;
						}
						}
						%>
						</tr>
					<% 
					}
					%>
			</table>
		</section>
	<!-- 検索前 メーカー順-->	
		<section id="maker">
			<table>
				<%
				int i2 = 0;
				int count2 = 1;
					for(int x = 0;x < makerList.size() / 4;x++){
				%>
						<tr>
				<%
						for (;i2 < makerList.size(); i2++) {
								ProductDto goods = (ProductDto) makerList.get(i2);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i2 == (4 * count2) - 1){
							count2 += 1;
							i2++;
							break;
						}
						}
						%>
						</tr>
					<% 
					}
					%>
			</table>
		</section>
	<!-- 検索前 値段順-->
		<section id="price">
			<table>
				<%
				int i3 = 0;
				int count3 = 1;
					for(int x = 0;x < priceList.size() / 4;x++){
				%>
						<tr>
				<%
						for (;i3 < priceList.size(); i3++) {
								ProductDto goods = (ProductDto) priceList.get(i3);
							%>
							<td class="tdProduct" id="<%=goods.getProductId()%>"><img
								src="images/<%=goods.getProductId()%>.jpg"> <br> <%=goods.getMaker() + " " + goods.getProductName()%> <br> <%=goods.getPrice()%>円</td>
						<%
						if(i3 == (4 * count3) - 1){
							count3 += 1;
							i3++;
							break;
						}
						}
						%>
						</tr>
					<% 
					}
					%>
			</table>
		</section>		
		
	</main>
	<%
	}
	%>
	<footer> &copy;2022 Let's☆就職☆ </footer>
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>