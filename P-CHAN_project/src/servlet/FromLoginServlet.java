package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.MemberDto;
import model.Crypto;
import model.LoginLogic;

/**
 * Servlet implementation class FromLoginServlet
 */
@WebServlet("/FromLoginServlet")
public class FromLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//登録、一覧画面へフォワード
		String flg = request.getParameter("flg");
		if(flg.equals("sign_up")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp");
			rd.forward(request, response);
		}
		if(flg.equals("return")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("memberId");//ログイン処理
		String hashPass = request.getParameter("hashPass");
		String path = "";
		HttpSession ses = request.getSession();
		LoginLogic ll = new LoginLogic();
		
		String challenge = (String)ses.getAttribute("CHALLENGE");
		
		Crypto c = new Crypto();//追記
		hashPass = c.sha256(hashPass) + challenge;
		hashPass = c.sha256(hashPass);
		
		String memberId = ll.login(loginId, hashPass, challenge);
		
		if(memberId != null) {
		    Cookie cookie = new Cookie("memberId",memberId);
		    cookie.setPath("/");
		    response.addCookie(cookie);
		    MemberDao mDao = new MemberDao();
		    MemberDto mDto = new MemberDto();
		    mDto = mDao.searchDetail(memberId);
		    ses.setAttribute("MEMBER", mDto);
		    path = "/WEB-INF/jsp/cookie.jsp";
		}
		
		if(memberId == null) {
			path = "/WEB-INF/jsp/login.jsp";
			ses.setAttribute("ERROR", "ERROR");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
