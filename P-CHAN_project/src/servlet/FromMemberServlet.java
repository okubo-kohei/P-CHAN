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

import dao.HistoryDao;
import dao.MemberDao;
import dto.HistoryDto;
import dto.MemberDto;
import model.Crypto;

/**
 * Servlet implementation class FromMemberServlet
 */
@WebServlet("/FromMemberServlet")
public class FromMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromMemberServlet() {
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
		String flg = request.getParameter("flg");
		String path = "";
		
		if(flg.equals("memChange")) {//会員情報変更処理
			MemberDto mem = new MemberDto();
			MemberDao memDao = new MemberDao();
			Cookie cookie[] = request.getCookies();
			String memberId = "";
			for (int i = 0 ; i < cookie.length ; i++){
				if (cookie[i].getName().equals("memberId")){
					memberId = cookie[i].getValue();
				}
			}
			mem = memDao.searchDetail(memberId);
			HttpSession ses = request.getSession();
			ses.setAttribute("MEMBER", mem);
			
			path = "/WEB-INF/jsp/member_change.jsp";
			//会員情報渡す
		}
		if(flg.equals("memDel")) {//会員情報削除処理
			MemberDto mem = new MemberDto();
			MemberDao memDao = new MemberDao();
			Cookie cookie[] = request.getCookies();
			String memberId = "";
			for (int i = 0 ; i < cookie.length ; i++){
				if (cookie[i].getName().equals("memberId")){
					memberId = cookie[i].getValue();
				}
			}
			mem = memDao.searchDetail(memberId);
			HttpSession ses = request.getSession();
			ses.setAttribute("MEMBER", mem);
			
			path = "/WEB-INF/jsp/member_del.jsp";
		}
		if(flg.equals("passChange")) {//パスワード変更処理
			Crypto cpt = new Crypto();
			String challenge = cpt.generateString(26);	//26桁のランダムワードの生成
			HttpSession ses = request.getSession();
			
			ses.setAttribute("CHALLENGE", challenge);
			
			path = "/WEB-INF/jsp/passwd_change.jsp";
		}
		if(flg.equals("history")) {//購入履歴表示
			HistoryDao hsDao = new HistoryDao();
			ArrayList<HistoryDto> historyList = new ArrayList<HistoryDto>();
			Cookie cookie[] = request.getCookies();
			String memberId = "";
			for (int i = 0 ; i < cookie.length ; i++){
				if (cookie[i].getName().equals("memberId")){
					memberId = cookie[i].getValue();
				}
			}
			historyList = hsDao.searchHistory(memberId);
			HttpSession ses = request.getSession();
			ses.setAttribute("HISTORY", historyList);
			
			path = "/WEB-INF/jsp/history.jsp";
			//購入履歴渡す
		}
		if(flg.equals("index")) {//一覧に戻る 追記
			path = "/WEB-INF/jsp/index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
