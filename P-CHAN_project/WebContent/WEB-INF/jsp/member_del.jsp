<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.MemberDao"%>
<%@page import="dto.MemberDto"%>
<!DOCTYPE html>
<!--12 会員情報削除-->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>p-chan||会員情報削除</title>
<link rel="stylesheet" href="css/member_del.css">
</head>
<body>
	<%
	HttpSession ses = request.getSession();
	MemberDto mem = new MemberDto();
	mem = (MemberDto)ses.getAttribute("MEMBER");
	%>
	<h1>会員情報削除</h1>
	<div id="inf">
		<div class="name">
			<p id="iname"><%=mem.getName()%></p>
			<img src="images/<%=mem.getRank()%>.png " alt="ランク画像" class="image">
			<!--/rank画像-->
		</div>
		<div class="tell-add">
			<p>
				住所:<%=mem.getPrefecture() + " " + mem.getMunicipality() + " " + mem.getAddress()%>
			</p>
			<p>
				<%
				if (mem.getBuilding() != null) {
				%>
				<%=mem.getBuilding()%>
				<%
				}
				%>
			</p>
			<p>
				TEL:<%=mem.getTel()%>
			</p>
		</div>
	</div>

	<div class="selection">
		<table>
			<tr>
				<th colspan="2"><h2>会員情報を削除してもよろしいですか</h2></th>
			</tr>
			<tr>
				<td><label class="open" for="pop-up">はい</label> 
				<input type="checkbox" id="pop-up">
					<div class="overlay">
						<div class="window">
							<label class="close" for="pop-up">×</label>
							<div>
								<p class="text">
									会員情報を削除すると<br>
									全ての情報が削除されます。<br>
									本当に削除しますか？
								</p>
<!--縦で表示されてしまう Formタグがでけえ-->
								<br>
									<form method="post" name="MovingDelEnd" action="FromMemberDelServlet">
										<a href="javascript:MovingDelEnd.submit()">はい</a>
									</form>
									<a href="MovingMember">いいえ</a>
								<!--16 会員情報表示-->
							
							
							
							</div>
						</div>
					</div></td>
				<td><a href="MovingMember">いいえ</a> <!--16 会員情報表示--></td>
			</tr>
		</table>





















	</div>

	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>

<footer> </footer>
</html>