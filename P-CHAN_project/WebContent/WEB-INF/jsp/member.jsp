<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@page import="dao.RankDao"%>
<!DOCTYPE html>
<!--/16 会員情報表示-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan||会員情報表示</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/member.css">
<%
HttpSession ses = request.getSession();
Cookie cookie[] = request.getCookies();
MemberDto mem = new MemberDto();
MemberDao memDao = new MemberDao();
String judge = null;
if (cookie != null) {
	for (int i = 0; i < cookie.length; i++) {
		if (cookie[i].getName().equals("memberId")) {
			mem = memDao.searchDetail(cookie[i].getValue());
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
						<input type="hidden" name="beforeLogout" value="member.jsp">
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

</head>
<body>
	<nav id="infl">
		<br>
		<p>
			<!--/name-->
		</p>
		<br> <br>
		<P>
			現在のポイント：<%=mem.getCurrentPoint()%>
			<!--/current_point-->
		</P>
		<br>

	</nav>

	<nav id="infr">
		<img src="images/<%=mem.getRank()%>.png" alt="ランク画像" class="image">
		<!--/rank画像-->
		<br>
		<!--/rank-->

	</nav>

	<nav id="member">
		<form action="FromMemberServlet" method="post">
			<ul>
				<li id="#">
					<!--/後入力-->
					<button type="submit" class="btn btn-member_del" value="memDel" name="flg">会員情報削除</button>
						<!--/会員情報削除-->
				</li>
				<li>
					<button type="submit" class="btn btn-passwd_change" value="passChange" name="flg">パスワード変更</button>
						<!--/パスワード変更-->
				</li>
				<li>
					<button type="submit" class="btn btn-member_change" value="memChange" name="flg">会員情報変更</button>
						<!--/会員情報変更-->
				</li>
				<li>
					<button type="submit" class="btn btn-buy_check" value="history" name="flg">購入履歴</button>
						<!--/購入履歴-->
				</li>
				<li>
					<button type="submit" class="btn btn-index" value="index" name="flg">一覧</button>
						<!--/一覧-->
				</li>
			</ul>
		</form>
	</nav>





	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>