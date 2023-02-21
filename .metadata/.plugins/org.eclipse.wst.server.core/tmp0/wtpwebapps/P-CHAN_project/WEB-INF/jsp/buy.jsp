<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dto.BuyProductDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!--6 購入-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan||購入</title>
<link rel="stylesheet" href="css/buy.css">
</head>

<body>
	<%
	HttpSession ses = request.getSession();
	int discount = (int) ses.getAttribute("DISCOUNT");
	MemberDto member = (MemberDto) ses.getAttribute("MEMBER");
	String post = Integer.toString(member.getPost());
	String post1 = post.substring(0, 3);
	String post2 = post.substring(3);
	ArrayList<BuyProductDto> buyList = (ArrayList<BuyProductDto>) ses.getAttribute("BUY");
	int sum = 0;


	for (int i = 0; i < buyList.size(); i++) {
		BuyProductDto buyProduct = buyList.get(i);
	%>
	<section id="product">
	<div class="products" id="<%=buyProduct.getProductId()%>">
		<img src="images/<%=buyProduct.getProductId()%>.jpg" alt="商品画像">
		<div id="description">
			<p>
				商品名
				<%=buyProduct.getProductName()%></p>
			<p>
				購入数
				<%=buyProduct.getQuantity()%>個
			</p>
			<p>
				金額
				<%=buyProduct.getPrice() * buyProduct.getQuantity()%>円
				<%
			sum += buyProduct.getPrice() * buyProduct.getQuantity();
			%>
			</p>
		</div>
		</div>
	</section>
	<%
	}
	%>
	<section id="rank">
		<p>
			ランクによる割引
			<%=discount%>％<br> 付加ポイント
			<%=sum / 100%>
		</p>
	</section>
	<p id="ir">
		<!--input required-->
		※は入力必須 
	</p>
	<script src="https://yubinbango.github.io/yubinbango/yubinbango.js"
		charset="UTF-8"></script>
	<form action="FromBuyServlet" class="h-adr" method="post">
		<span class="p-country-name" style="display: none;">Japan</span>
		<%
		if (member != null) {
		%>
		<dl>
			<dt>
				<span class="must">※</span>氏名
			</dt>
			<dd>
				<input type="text" name="name" value="<%=member.getName()%>"
					required>
			</dd>

			<dt>
				<span class="must">※</span>電話番号
			</dt>
			<dd>
				<input type="tel" name="tel" value="<%=member.getTel()%>" required>
			</dd>

			<dt>
				<span class="must">※</span>郵便番号
			</dt>
			<dd>
				<input type="text" class="p-postal-code" size="3" maxlength="3"
					value="<%=post1%>" required> <span id="hyphen">-</span> <input
					type="text" class="p-postal-code" size="4" maxlength="4"
					value="<%=post2%>" required>
			</dd>

			<dt>
				<span class="must">※</span>都道府県
			</dt>
			<dd>
				<input type="text" class="p-region" list="pref"
					placeholder="都道府県を選択する" value="<%=member.getPrefecture()%>"
					required />
			</dd>


			<dt>
				<span class="must">※</span>市区町村
			</dt>
			<dd>
				<input type="text" class="p-locality"
					value="<%=member.getMunicipality()%>" required />
			</dd>

			<dt>丁目・番地・号</dt>
			<dd>
				<input type="text" class="p-street-address p-extended-address"
					value="<%=member.getAddress()%>" required />
			</dd>

			<dt>建物名・部屋番号</dt>
			<dd>
				<input type="text" name="room" value="<%=member.getBuilding()%>">
			</dd>

			<dt>
				<span class="must">※</span>支払い方法
			</dt>
			<dd>
				<select name="pey" required>
					<option hidden>"コンビニ/クレジット/代金引換"</option>
					<option value="コンビニ">コンビニ</option>
					<option value="クレジット">クレジット</option>
					<option value="代金引換">代金引換</option>
				</select>
			</dd>

			<dt>使用ポイント</dt>
			<dd>
				<input type="number" name="current_point" id="current_point"
					maxlength="11" min=0 max="<%=member.getCurrentPoint()%>"value=0>
			</dd>

		</dl>
		<%
		}
		%>
		<input type="submit" value="確定" id="check_url">
		<!--7 購入確認-->
	</form>
	<form action="MovingCart">
		<input type="submit" value="カートへ" id="cart_url">
	</form>
	<!--3 カート-->

	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/img.js"></script>

	<datalist id="pref">
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