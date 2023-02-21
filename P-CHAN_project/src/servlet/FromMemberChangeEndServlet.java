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

/**
 * Servlet implementation class FromMemberChangeEndServlet
 */
@WebServlet("/FromMemberChangeEndServlet")
public class FromMemberChangeEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromMemberChangeEndServlet() {
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
		Cookie cookie[] = request.getCookies();
		String path = "";
		
		if (cookie != null){
			String memberId = "";
			for (int i = 0 ; i < cookie.length ; i++){
				if (cookie[i].getName().equals("memberId")){
					memberId = cookie[i].getValue();
				}
			}
			
			MemberDao mDao = new MemberDao();
			MemberDto mDto = new MemberDto();
			mDto = mDao.searchDetail(memberId);
			
			HttpSession ses = request.getSession();
			ses.setAttribute("MEMBER", mDto);
			path = "/WEB-INF/jsp/member.jsp";
		}
		
		if(cookie == null) {
			path = "/WEB-INF/jsp/index.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
