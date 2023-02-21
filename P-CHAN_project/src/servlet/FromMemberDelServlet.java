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

import dto.MemberDto;
import model.DeleteLogic;

/**
 * Servlet implementation class FromMemberDelServlet
 */
@WebServlet("/FromMemberDelServlet")
public class FromMemberDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromMemberDelServlet() {
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
		MemberDto mem = new MemberDto();
		mem = (MemberDto)ses.getAttribute("MEMBER");
		String memberId = mem.getMemberId();
		DeleteLogic del = new DeleteLogic();
		boolean rtn = del.delete(memberId);//削除成功の可否（未利用）
		
		Cookie cookie[] = request.getCookies();

		if (cookie != null){//ログアウト処理
			Cookie cookieDel = new Cookie("memberId","");
		    cookieDel.setPath("/");
		    cookieDel.setMaxAge(0);
		    response.addCookie(cookieDel);
		}
		
		String path = "/WEB-INF/jsp/member_del_end.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
