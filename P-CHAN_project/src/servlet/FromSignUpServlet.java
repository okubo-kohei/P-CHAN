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
import model.Crypto;
import model.SignUpLogic;

/**
 * Servlet implementation class FromSignUpServlet
 */
@WebServlet("/FromSignUpServlet")
public class FromSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromSignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");//戻るボタン
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//登録処理
		request.setCharacterEncoding("UTF-8");
		MemberDto member = new MemberDto();
		Crypto cpt = new Crypto();
		SignUpLogic sLogic = new SignUpLogic();
		
		String name = request.getParameter("name");
		int post = Integer.parseInt(request.getParameter("post1") + request.getParameter("post2")); //変更点
		String prefecture = request.getParameter("prefecture");
		String municipality = request.getParameter("municipality");
		String address = request.getParameter("address");
		String building = request.getParameter("building");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		int currentPoint = 0;
		int cumulativePoint = 0;
		int rank = 0;
		
		password = cpt.sha256(password);
		
		//memberIdのみnull SignUpLogic内にて追加
		member.setMail(mail);
		member.setPassword(password);
		member.setName(name);
		member.setPost(post);
		member.setPrefecture(prefecture);
		member.setMunicipality(municipality);
		member.setAddress(address);
		member.setBuilding(building);
		member.setTel(tel);
		member.setCurrentPoint(currentPoint);
		member.setCumulativePoint(cumulativePoint);
		member.setRank(rank);
		
		boolean signUp = sLogic.signUp(member);//登録成功ならtrue（未利用)
		String url = "/WEB-INF/jsp/sign_up_end.jsp";
		if(!signUp) {
			url = "/WEB-INF/jsp/sign_up.jsp";
			HttpSession ses = request.getSession();
			ses.setAttribute("SIGNUPERROR", "ERROR");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
