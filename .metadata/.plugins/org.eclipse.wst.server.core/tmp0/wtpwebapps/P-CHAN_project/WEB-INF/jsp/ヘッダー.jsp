<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><!---->
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>p-chan</title>
  <link rel="stylesheet" href="css/style.css">

<%
	HttpSession ses = request.getSession();
	Cookie cookie[]= request.getCookies();
	String judge = null;
	if(cookie != null){
   		for(int i = 0; i < cookie.length; i++){
        	if(cookie[i].getName().equals("memberId")){
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
				<li id="searsub"><input type="search" id="search" name="searchWords" placeholder="キーワードを入力">
				<button type="submit" id="submit" value="search" name="flg">検索</button>
				</li>
				<!--/会員情報表示-->
				<li>
				<%
					if(judge != null){
				%>
					<div class="profile icon"></div><button type="submit" value="member" name="flg">会員情報</button>
				<% 
					} else {
				%>
					<div class="profile icon"></div><button type="submit" value="login" name="flg">会員情報</button>
				<%
					} 
				%>
				</li>
				<!--/カート画面-->
				<li>
				<%
					if(judge != null){
				%>
					<div class="cart icon"></div><i class="gg-shopping-cart"></i><button type="submit" value="cart" name="flg">カート</button>
				<% 
					} else {
				%>
					<div class="cart icon"></div><i class="gg-shopping-cart"></i><button type="submit" value="login" name="flg">カート</button>
				<%
					} 
				%>
				</li>
				<li><!--/ログイン画面-->
					<div id="login">						
						<% 
	 						if (judge != null) {
	 					%>
							<input type = "hidden" name = "beforeLogout" value="index.jsp">
							<div class="key icon"></div><button type="submit" value="logout" name="flg">ログアウト</button> 
						<%
	 						} else {
	 					%>
							<div class="key icon"></div><button type="submit" value="login" name="flg">ログイン</button>
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


  <script src = "js/jquery-3.6.0.min.js"></script>
  <script src="js/app.js"></script>
</body>

<footer>
  
</footer>
</html>