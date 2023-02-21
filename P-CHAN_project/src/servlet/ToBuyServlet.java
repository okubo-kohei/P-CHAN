package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BuyProductDto;
import dto.MemberDto;
import dto.ProductDto;
import model.RankLogic;

/**
 * Servlet implementation class ToBuyServlet
 */
@WebServlet("/ToBuyServlet")
public class ToBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToBuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cart.jsp, goods.jspからの遷移判定、および購入確認画面からの戻る処理もここで行う
		//それぞれのjspにflgを入れ、取得し判定
		
		HttpSession ses = request.getSession();
		
		String flg = request.getParameter("flg"); //カートからか商品画面からか購入確認からの戻りかの判定をしたい
		ArrayList<BuyProductDto> buyList = new ArrayList<BuyProductDto>();
		
		MemberDto member = (MemberDto)ses.getAttribute("MEMBER");
		
		RankLogic rankLogic = new RankLogic();
		int discount = rankLogic.dicountSearch(member.getRank());
		
		if(flg.equals("cart")) {
			buyList = (ArrayList<BuyProductDto>)ses.getAttribute("CART");
			ses.setAttribute("DISCOUNT", discount);
			ses.setAttribute("RETURN_PATH", "/WEB-INF/jsp/cart.jsp"); //次のページで戻るボタンを押した際のパスを記述しておく
		}
		if(flg.equals("product")) {
			String stringQuantity = request.getParameter("quantity");
			int quantity = Integer.parseInt(stringQuantity);
			ProductDto product = (ProductDto)ses.getAttribute("FROMPRODUCT");
			BuyProductDto buyProduct = new BuyProductDto(product);
			buyProduct.setQuantity(quantity);
			buyList.add(buyProduct);
			ses.setAttribute("DISCOUNT", discount);
			ses.setAttribute("RETURN_PATH", "/WEB-INF/jsp/goods.jsp"); //次のページで戻るボタンを押した際のパスを記述しておく
		}
		if(flg.equals("return")) {
			buyList = (ArrayList<BuyProductDto>)ses.getAttribute("BUY");
		}
		
		ses.setAttribute("BUY", buyList);
		
		String path = "/WEB-INF/jsp/buy.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
