<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html><!--15 カスタマーサポート-->
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>title</title>
  <link rel="stylesheet" href="css/contact.css" />
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

  <h1>お問い合わせ</h1><br>
  <textarea id = "box" name = "text" cols = "100" rows = "25" class = "ondes"placeholder="ここに文字を入力してください"></textarea>
   <form action="MovingIndex">
   <button type="submit" class="btn btn-index">　一　覧　</button>
   </form>
  <button type="submit" class="btn btn-contact" id="send">　送　信　</button>                     <!--ここを押すことによって19行目を表示-->
  <div id = "attention"></div>
  <script src = "js/jquery-3.6.0.min.js"></script>
  <script src="js/app.js"></script>
  <script src="js/send.js"></script>
</body>
</html>