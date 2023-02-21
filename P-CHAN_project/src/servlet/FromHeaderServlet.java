package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dao.ProductDao;
import dto.MemberDto;
import dto.ProductDto;
import model.Crypto;
import model.SearchLogic;

/**
 * Servlet implementation class FromHeaderServlet
 */
@WebServlet("/FromHeaderServlet")
public class FromHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromHeaderServlet() {
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
		//各ページへ遷移
				String flg = request.getParameter("flg");
				String path = "/WEB-INF/jsp/login.jsp";
				
				if(flg.equals("member")) {
					Cookie cookie[] = request.getCookies();
					
					if (cookie != null){
						String memberId = "";
						for (int i = 0 ; i < cookie.length ; i++){
							if (cookie[i].getName().equals("memberId")){
								memberId = cookie[i].getValue();
							}
						}
						
						MemberDao mDto = new MemberDao();
						MemberDto mDao = new MemberDto();
						mDao = mDto.searchDetail(memberId);
						
						HttpSession ses = request.getSession();
						ses.setAttribute("MEMBER", mDao);
						path = "/WEB-INF/jsp/member.jsp";
					}
					
					if(cookie == null) {
						path = "/WEB-INF/jsp/index.jsp";
					}
					
				}
				if(flg.equals("cart")) {
					//カートの中身の取得？
					path = "/WEB-INF/jsp/cart.jsp";
				}
				if(flg.equals("login")) {
					Crypto cpt = new Crypto();
					String challenge = cpt.generateString(26);	//26桁のランダムワードの生成
					HttpSession ses = request.getSession();
					ses.setAttribute("CHALLENGE", challenge);
					path = "/WEB-INF/jsp/login.jsp";
				}
				if(flg.equals("logout")) {
					String before = request.getParameter("beforeLogout");//tyuuisiro
					HttpSession ses = request.getSession();
					ses.setAttribute("BEFORE", before);
					path = "/WEB-INF/jsp/logout.jsp";
				}
				if(flg.equals("search")) {
					SearchLogic sl = new SearchLogic();
					String word = request.getParameter("searchWords");
					
					ArrayList<ProductDto> productList = sl.listSearch(word);
					
					ProductDao product = new ProductDao();
					ArrayList<ProductDto> nameList = product.searchSortName(word);
					ArrayList<ProductDto> makerList = product.searchSortMaker(word);
					ArrayList<ProductDto> priceList = product.searchSortprice(word);
					
					HttpSession ses = request.getSession();
					ses.setAttribute("PRODUCTLIST", productList);
					ses.setAttribute("NAMELIST", nameList);
					ses.setAttribute("MAKERLIST", makerList);
					ses.setAttribute("PRICELIST", priceList);
					
					path = "/WEB-INF/jsp/index.jsp";
				}
				
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
	}
	
	

}
