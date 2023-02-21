<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--18 ログアウト-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>title</title>
<link rel="stylesheet" href="css/logout.css">
</head>
<body>
	<h1>LOGOUT</h1>
	<p>ログアウトします。よろしいですか?</p>

	<form action="FromLogoutServlet" method="post">
		<div id="yes"><button type="submit" id="submit" value="yes" name="logout">はい</button></div>
		<div id="no"><button type="submit" id="submit" value="no" name="logout">いいえ</button></div>
	</form>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>