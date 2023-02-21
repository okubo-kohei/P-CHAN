<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html><!--11 会員情報変更完了 -->
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>p-chan||会員情報変更完了</title>
  <link rel="stylesheet" href="css/member_change_end.css">
</head>
<body>
  <h1>会員情報が変更されました</h1>
  <form action="FromMemberChangeEndServlet" method="post">
  <input type="submit" value="会員情報へ" id="mem_url"><!--14 会員情報表示-->
  </form>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script src="js/app.js"></script>
</body>
</html>