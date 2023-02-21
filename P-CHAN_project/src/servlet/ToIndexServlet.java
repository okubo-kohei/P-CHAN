package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dto.ProductDto;

/**
 * Servlet implementation class FromMemberDelEndServlet
 */
@WebServlet("/ToIndexServlet")
public class ToIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao product = new ProductDao();
		ArrayList<ProductDto> allList = new ArrayList<ProductDto>();
		ArrayList<ProductDto> nameList = new ArrayList<ProductDto>();
		ArrayList<ProductDto> makerList = new ArrayList<ProductDto>();
		ArrayList<ProductDto> priceList = new ArrayList<ProductDto>();

		allList = product.allProduct();
		nameList = product.nameSort();
		makerList = product.makerSort();
		priceList = product.priceSort();
		

		HttpSession ses = request.getSession();
		ses.setAttribute("ALLLIST", allList);
		ses.setAttribute("NAMELIST", nameList);
		ses.setAttribute("MAKERLIST", makerList);
		ses.setAttribute("PRICELIST", priceList);

		String path = "/WEB-INF/jsp/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
