package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDto;
import model.ChangeMemberLogic;

/**
 * Servlet implementation class FromMemberChangeServlet
 */
@WebServlet("/FromMemberChangeServlet")
public class FromMemberChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromMemberChangeServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String path = "/WEB-INF/jsp/member.jsp";
		String flg = request.getParameter("flg");
		
		if(flg.equals("change")) {
			MemberDto member = new MemberDto();
			ChangeMemberLogic mLogic = new ChangeMemberLogic();
			
			Cookie cookie[] = request.getCookies();
			String memberId = "";
			
			if (cookie != null){
				for (int i = 0 ; i < cookie.length ; i++){
					if (cookie[i].getName().equals("memberId")){
						memberId = cookie[i].getValue();
					}
				}
			}
			
			String mail = request.getParameter("mail");
			System.out.println(mail);
			String name = request.getParameter("name");
			int post = Integer.parseInt(request.getParameter("post1") + request.getParameter("post2"));
			String prefecture = request.getParameter("prefecture");
			System.out.println(prefecture);
			String municipality = request.getParameter("municipality");
			String address = request.getParameter("address");
			String building = request.getParameter("building");
			String tel = request.getParameter("tel");
			
			member.setMemberId(memberId);
			member.setMail(mail);
			member.setName(name);
			member.setPost(post);
			member.setPrefecture(prefecture);
			member.setMunicipality(municipality);
			member.setAddress(address);
			member.setBuilding(building);
			member.setTel(tel);
			
			boolean rtn = mLogic.changeMember(member);//登録成功ならtrue（未利用）
			System.out.println(rtn);
			
			path = "/WEB-INF/jsp/member_change_end.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
