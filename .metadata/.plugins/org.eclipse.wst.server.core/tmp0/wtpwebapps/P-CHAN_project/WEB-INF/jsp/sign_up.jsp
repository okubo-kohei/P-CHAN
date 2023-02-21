<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--4 会員情報登録-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan</title>
<link rel="stylesheet" href="css/sign_up.css">
</head>
<body>
<%
HttpSession ses = request.getSession();
String error = (String)ses.getAttribute("SIGNUPERROR");
%>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/passwordCheck.js"></script>
	<h1>登録画面</h1>
	<p>※は入力必須</p>
	
	<script src="https://yubinbango.github.io/yubinbango/yubinbango.js"
		charset="UTF-8"></script>
	<!--住所読み込み-->
	<form action="FromSignUpServlet" class="h-adr" method="post">
	<%
		if(error != null){
	%>
			<font color="red">このメールアドレスは使用されています</font>
	<%
		}
	%>
		<span class="p-country-name" style="display: none;">Japan</span>
		<dl>
			<dt>
				<span class="must">※</span>氏名
			</dt>
			<dd>
				<input type="text" name="name" id="name" required>
			</dd>

			<dt>
				<span class="must">※</span>郵便番号
			</dt>
			<dd>
				<input type="text" name="post1" class="p-postal-code" size="3"
					maxlength="3" required> - <input type="text" name="post2"
					class="p-postal-code" size="4" maxlength="4" required>
			</dd>

			<dt>
				<span class="must">※</span>都道府県
			</dt>
			<dd>
				<input type="text" name="prefecture" class="p-region" list="pref"
					placeholder="都道府県を選択する" required />
			</dd>


			<dt>
				<span class="must">※</span>市区町村
			</dt>
			<dd>
				<input type="text" name="municipality" class="p-locality" required />
			</dd>

			<dt>
				<span class="must">※</span>丁目・番地・号
			</dt>
			<dd>
				<input type="text" name="address"
					class="p-street-address p-extended-address" required />
			</dd>

			<dt>建物名・部屋番号</dt>
			<dd>
				<input type="text" name="building" id="roomname">
			</dd>

			<dt>
				<span class="must">※</span>電話番号(-を除く)
			</dt>
			<dd>
				<input type="tel" name="tel" id="tel" required>
			</dd>

			<dt>
				<span class="must">※</span>メールアドレス
			</dt>
			<dd>
				<input type="email" name="mail" id="mail" required>
			</dd>

			<dt>
				<span class="must">※</span>パスワード（8文字以上）
			</dt>
			<dd>
				<input type="password" name="password" id="password" minlength="8" required>
			</dd>

			<dt>
				<span class="must">※</span>パスワード(確認)
			</dt>
			<dd>
				<input type="password" name="passwordCheck" id="passwordCheck"
					oninput="checkPassword(this)" required>
			</dd>
		</dl>
		<input type="submit" value="登録">
		<!--5 会員情報登録完了-->
	</form>
	<form action="MovingLogin">
		<input type="submit" value="戻る">
	</form>
	<!--1 ログイン-->

	<datalist id="pref">
		<!--都道府県-->
		<option value="北海道">北海道</option>
		<option value="青森県">青森県</option>
		<option value="岩手県">岩手県</option>
		<option value="宮城県">宮城県</option>
		<option value="秋田県">秋田県</option>
		<option value="山形県">山形県</option>
		<option value="福島県">福島県</option>
		<option value="茨城県">茨城県</option>
		<option value="栃木県">栃木県</option>
		<option value="群馬県">群馬県</option>
		<option value="埼玉県">埼玉県</option>
		<option value="千葉県">千葉県</option>
		<option value="東京都">東京都</option>
		<option value="神奈川県">神奈川県</option>
		<option value="新潟県">新潟県</option>
		<option value="富山県">富山県</option>
		<option value="石川県">石川県</option>
		<option value="福井県">福井県</option>
		<option value="山梨県">山梨県</option>
		<option value="長野県">長野県</option>
		<option value="岐阜県">岐阜県</option>
		<option value="静岡県">静岡県</option>
		<option value="愛知県">愛知県</option>
		<option value="三重県">三重県</option>
		<option value="滋賀県">滋賀県</option>
		<option value="京都府">京都府</option>
		<option value="大阪府">大阪府</option>
		<option value="兵庫県">兵庫県</option>
		<option value="奈良県">奈良県</option>
		<option value="和歌山県">和歌山県</option>
		<option value="鳥取県">鳥取県</option>
		<option value="島根県">島根県</option>
		<option value="岡山県">岡山県</option>
		<option value="広島県">広島県</option>
		<option value="山口県">山口県</option>
		<option value="徳島県">徳島県</option>
		<option value="香川県">香川県</option>
		<option value="愛媛県">愛媛県</option>
		<option value="高知県">高知県</option>
		<option value="福岡県">福岡県</option>
		<option value="佐賀県">佐賀県</option>
		<option value="長崎県">長崎県</option>
		<option value="熊本県">熊本県</option>
		<option value="大分県">大分県</option>
		<option value="宮崎県">宮崎県</option>
		<option value="鹿児島県">鹿児島県</option>
		<option value="沖縄県">沖縄県</option>
	</datalist>
</body>

<footer> </footer>

</html>