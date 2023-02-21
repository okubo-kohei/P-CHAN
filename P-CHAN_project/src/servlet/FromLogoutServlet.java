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

/**
 * Servlet implementation class FromLogoutServlet
 */
@WebServlet("/FromLogoutServlet")
public class FromLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromLogoutServlet() {
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
		String logout = request.getParameter("logout");	//はい、いいえボタン判別処理
		
		if(logout.equals("yes")) {	//ログアウト処理（クッキーを塗り替える）
			Cookie cookie[] = request.getCookies();

			if (cookie != null){
				Cookie cookieDel = new Cookie("memberId","");
			    cookieDel.setPath("/");
			    cookieDel.setMaxAge(0);
			    response.addCookie(cookieDel);
			}
			HttpSession ses = request.getSession();
			ses.removeAttribute("ERROR");
			
			
			String path = "/WEB-INF/jsp/cookiedel.jsp"; //変更点
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
		
		if(logout.equals("no")) { //いいえの場合直前のページに戻す処理
			HttpSession ses = request.getSession();
			String path = (String)ses.getAttribute("BEFORE"); //FromHeaderにて定義
			path = "/WEB-INF/jsp/" + path;
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}

}
