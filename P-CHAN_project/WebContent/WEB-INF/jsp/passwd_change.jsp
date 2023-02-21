<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.Crypto"%>
<!DOCTYPE html>
<!--10 パスワード変更-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan||パスワード変更</title>
<link rel="stylesheet" href="css/passwd_change.css">
<script src="js/passwordCheck.js"></script>
<script src="js/sha256.js"></script>
</head>
<%
HttpSession ses = request.getSession();
String challenge = (String)ses.getAttribute("CHALLENGE");
String error = (String)ses.getAttribute("ERRORPWD");
%>
<body>
	<h1>パスワード変更</h1>
	<p id="ir">
		<!--input required-->
		※は入力必須
	</p>
	<%
		if(error != null){
	%>
			パスワードの変更に失敗しました
	<%
		}
	%>
	<form action="FromPasswdChangeServlet" method="post" id="form">
		<dl>
			<dt>
			<!-- ここから -->
				<span class="must">※</span>現在のパスワード
			</dt>
			<dd>
				<input type="hidden" type="password" value="<%=challenge%>" name="challenge" id="challenge">
				<input type="password" name="nowPass" id="nowPass" required>
			</dd>

			<dt>
				<span class="must">※</span>新しいパスワード
			</dt>
			<dd>
				<input type="password" name="password" id="password" minlength="8"required>
			</dd>
			<dt>
				<span class="must">※</span>新しいパスワード(確認)
			</dt>
			<dd>
				<input type="password" name="passwordCheck" id="passwordCheck"
					oninput="checkPassword(this)" required>
			</dd>
		</dl>
		<input type="submit" value="確定" id="cng_url">
		<!--11 会員情報変更完了-->
	</form>
	<form action="MovingMember">
		<input type="submit" value="会員情報へ" id="mem_url">
	</form>
	<!--16 会員情報表示-->
</body>

<footer> </footer>
</html>