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

import dao.ProductDao;
import dto.BuyProductDto;
import dto.ProductDto;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		HttpSession ses = request.getSession();
		ArrayList<BuyProductDto> buyList;
		
		if(ses.getAttribute("CART") == null) { //追記
			buyList = new ArrayList<BuyProductDto>();
		}else {
			buyList = (ArrayList<BuyProductDto>)ses.getAttribute("CART");
		}
		
		//カート追加商品の取得
		String productId = request.getParameter("productId");
		ProductDao pDao = new ProductDao();
		ProductDto product = pDao.searchDetail(productId);
		BuyProductDto buy = new BuyProductDto(product); 
		
		//追記
		String stringQuantity = request.getParameter("formQuantity");
		int quantity =Integer.parseInt(stringQuantity); 
		buy.setQuantity(quantity);
		 
		boolean flg = false;
		
		for(int i = 0;i < buyList.size();i++) {
			BuyProductDto checkBuy = buyList.get(i);
			String stock = checkBuy.getProductId();
			if(stock.equals(productId)) {
				int addQuantity = checkBuy.getQuantity();
				addQuantity += quantity;
				checkBuy.setQuantity(addQuantity);
				buyList.set(i, checkBuy);
				flg = true;
				break;
			}
			
		}
		
		if(flg == false) {
			buyList.add(buy);
		}
		
		
		ses.setAttribute("CART", buyList); //セッションへ保存
		
		String path = "/WEB-INF/jsp/cart.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
