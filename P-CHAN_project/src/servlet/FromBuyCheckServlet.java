package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HistoryDao;
import dto.BuyProductDto;
import dto.MemberDto;
import model.BuyLogic;
import model.RankLogic;

/**
 * Servlet implementation class FromBuyCheckServlet
 */
@WebServlet("/FromBuyCheckServlet")
public class FromBuyCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromBuyCheckServlet() {
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
		//購入確認画面から購入完了画面への遷移処理
		//購入可能かの在庫判定もここで行う
		HttpSession ses = request.getSession();
		ArrayList<BuyProductDto> buyList = (ArrayList<BuyProductDto>)ses.getAttribute("BUY");
		MemberDto member = (MemberDto)ses.getAttribute("MEMBER");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		BuyLogic buyLogic = new BuyLogic();
		boolean buyCheck = buyLogic.buy(buyList);
		String memberId = member.getMemberId();
		
		String path = "";
		
		if(buyCheck == true) { //購入成功の場合
			path = "/WEB-INF/jsp/buy_end.jsp";
			
			//購入履歴の作成、更新
			HistoryDao hDao = new HistoryDao();
			hDao.addHistory(buyList, memberId, timestamp);
			
			//ランク更新処理
			String point = (String)ses.getAttribute("POINT");
			if(point.equals("")) {
				point = "0";
			}
			int point2 = Integer.parseInt(point);
			RankLogic rankLogic = new RankLogic();
			rankLogic.rankUpdate(buyList, memberId, point2);
		}
		
		if(buyCheck == false) { //購入失敗の場合
			path = "/WEB-INF/jspbuy_error.jsp"; //購入失敗画面へのパスを記述する
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
