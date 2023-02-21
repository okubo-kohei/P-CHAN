<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.security.MessageDigest"%>
<!DOCTYPE html>
<!--1 ログイン-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>title</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%
	HttpSession ses = request.getSession();
	String error = (String) ses.getAttribute("ERROR");
	%>
	<div class="main">
	<form action="FromLoginServlet" method="post">
		<div class="login_form_top">
			<h1>LOGIN</h1>
			<p>
				メールアドレスか電話番号、パスワードをご入力の上<br>「LOGIN」ボタンをクリックしてください。
			</p>
		</div>
		<div class="login_form_btm">
			<!--id、name  -->
			<input type="text" id="memberId" name="memberId"
				placeholder="Address">
			<!-- passward -->
			<input type="password" id="password" name="hashPass"
				placeholder="Password"><br> <input type="submit"
				name="botton" value="LOGIN">
		</div>
	</form>
	<%
	if (error != null) {
		if (error.equals("ERROR")) {
	%>
	<font color="red">ログインに失敗しました。</font>
	<br>
	<%
	}
	}
	%>
	<a href="Moving_sign_up" id="account">アカウントを作成する</a>
	<!--4 会員情報登録画面-->
	<a href="MovingIndex" id="back">一覧に戻る</a>
	<!--2 商品一覧画面-->
	</div>
	
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>