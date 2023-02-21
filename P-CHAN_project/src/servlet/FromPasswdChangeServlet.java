package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import model.ChangePassLogic;
import model.Crypto;
import model.LoginLogic;

/**
 * Servlet implementation class FromMemberChangeServlet
 */
@WebServlet("/FromPasswdChangeServlet")
public class FromPasswdChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromPasswdChangeServlet() {
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
		MemberDto mem = new MemberDto();
		HttpSession ses = request.getSession();
		mem = (MemberDto)ses.getAttribute("MEMBER");//セッションよりメンバーIDの取得
		String memberId = mem.getMemberId();
		String challenge = (String)ses.getAttribute("CHALLENGE");//チャレンジの取得
		String nowPass = request.getParameter("nowPass");//取得した旧パスワード
		
		Crypto c = new Crypto();//追記
		nowPass = c.sha256(nowPass) + challenge;
		nowPass = c.sha256(nowPass);
		
		LoginLogic login = new LoginLogic();
		
		String path = "";
		boolean rtn = login.loginChange(memberId, nowPass, challenge);
		
		if(rtn) {//パスワード検証成功
			ChangePassLogic changePass = new ChangePassLogic();
			String newPass = request.getParameter("password");
			boolean tryChange = changePass.changePass(memberId, newPass);//booleanにて結果帰ってくる（未利用）
			
			path = "/WEB-INF/jsp/member_change_end.jsp";
		}
		if(!rtn) {//失敗
			ses.setAttribute("ERRORPWD", "ERROR");
			path = "/WEB-INF/jsp/passwd_change.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
