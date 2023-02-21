<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dto.BuyProductDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!--7 購入確認-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan||購入確認</title>
<link rel="stylesheet" href="css/buy_check.css">
</head>
<body>
	<h2>こちらの商品を購入します。よろしいですか？</h2>
	<%
	HttpSession ses = request.getSession();
	ArrayList<BuyProductDto> buyList = (ArrayList<BuyProductDto>) ses.getAttribute("BUY");
	MemberDto member = (MemberDto)ses.getAttribute("MEMBER");
	for (int i = 0; i < buyList.size(); i++) {
		BuyProductDto buyProduct = buyList.get(i);
	%>
	<div id="product">
		<img src="images/<%=buyProduct.getProductId()%>.jpg" alt="商品画像">
		<div id="description">
			<p id="p_name">商品名<%=buyProduct.getProductName()%></p>
			<br>
			<p id="destination">お届け場所<%=member.getPrefecture() + member.getMunicipality() + member.getAddress() + member.getBuilding() %></p>
		</div>
	</div>
	<%
	}
	%>
	<form action="FromBuyCheckServlet" method="post">
		<input type="submit" value="はい" id="yes_url"> 
	</form>
	<form action="ToBuyServlet" method="post">
		<input type="submit" value="いいえ" id="no_url">
		<input type="hidden" name="flg" value="return">
	</form>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>

<footer> </footer>
</html>