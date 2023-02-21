<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dto.HistoryDto"%>
<%@page import="dto.ProductDto"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!--14 購入履歴表示-->
<html lang="ja">

<head>
<meta charset="UTF-8">
<title>p-chan||購入履歴</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/history.css">
</head>
<%
HttpSession ses = request.getSession();
ArrayList<HistoryDto> history = (ArrayList<HistoryDto>)ses.getAttribute("HISTORY");
%>
<body>

	<h1>購入履歴一覧</h1>


	<table>
		<tbody>
			<%
			if (history.size() != 0) {
				for (int i = 0; i < history.size(); i++) {
					HistoryDto img = new HistoryDto();
					ProductDto product = new ProductDto();
					ProductDao pDao = new ProductDao();
					img = history.get(i);
					product = pDao.searchDetail(img.getProductId());
			%>
					<tr>
						<th>
							<form action="FromHistoryServlet" method="post">
								<button type="submit">
									<input type="hidden" value="<%=img.getProductId() %>" name="productId">
									<img src="images/<%=img.getProductId()%>.jpg">
								</button>
							</form>
						</th>
		
						<td>
							<p>
								<%=product.getProductName() %>を<%=img.getQuantity() %>個購入しました
								
							</p>
						</td>
					</tr>
				<%
				}
				}else{
				%>
					<p id="noProduct">購入した商品はありません</p>
				<%
				}
				%>




		</tbody>
	</table>


	<div id="member">
		<form action="MovingMember">
			<button type="submit" class="btn btn-member">会員情報に戻る</button>
		</form>
		<!--16 会員情報表示-->
	</div>


	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/app.js"></script>
</body>

<footer> </footer>
</html>