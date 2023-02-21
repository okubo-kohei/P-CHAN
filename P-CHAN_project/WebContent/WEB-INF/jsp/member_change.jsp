<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<!DOCTYPE html><!--9 会員情報変更-->
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>p-chan||会員情報変更</title>
  <link rel="stylesheet" href="css/member_change.css">

</head>
<body>
<%
	HttpSession ses = request.getSession();
	MemberDto member =(MemberDto)ses.getAttribute("MEMBER");
	String post = Integer.toString(member.getPost());
	String post1 = post.substring(0,3);
	String post2 = post.substring(3);
	
%>
  <h1>会員情報変更</h1>
  <script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
  <form action="FromMemberChangeServlet" method="post" class="h-adr">
    
    <span class="p-country-name" style="display:none;">Japan</span>
    <dl>
      <dt>氏名</dt>
      <dd><input type="text" name="name" id="name" value="<%=member.getName() %>"></dd>

      <dt>メールアドレス</dt>
      <dd><input type="email" name="mail" id="mail" value="<%=member.getMail()%>"></dd>

      <dt>電話番号</dt>
      <dd><input type="tel" name="tel" id="tel" value="<%=member.getTel()%>"></dd>
      
      <dt>郵便番号</dt>
      <dd><input type="text" class="p-postal-code" size="3" maxlength="3" name="post1" value="<%=post1%>">
      <span id="hyphen">-</span>
      <input type="text" class="p-postal-code" size="4" maxlength="4" name="post2" value="<%=post2%>"></dd>

      <dt>都道府県</dt>
      <dd><input type="text" class="p-region" list="pref" placeholder="都道府県を選択する" name="prefecture" value="<%= member.getPrefecture()%>"></dd>

      <dt>市区町村</dt>
      <dd><input type="text" class="p-locality" name="municipality" value="<%=member.getMunicipality()%>"></dd>
      
      <dt>丁目・番地・号</dt>
      <dd><input type="text" class="p-street-address p-extended-address"  name="address" value="<%=member.getAddress()%>"></dd>

      <dt>建物名・部屋番号</dt>
      <dd><input type="text" id="roomname" name="building" value="<%=member.getBuilding()%>"></dd>
      
    </dl>
    <button type="submit" value="change" id="chg_url" name="flg">確定</button><!--11 会員情報変更完了--> 
  	<button type="submit" value="back" id="mem_url" name="flg">会員情報へ</button><!--16 会員情報表示-->
  	</form>


  <script src = "js/jquery-3.6.0.min.js"></script>
  <script src="js/app.js"></script>

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

<footer>
  
</footer>
</html>