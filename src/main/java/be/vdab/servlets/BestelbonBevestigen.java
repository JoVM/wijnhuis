package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;

/**
 * Servlet implementation class BestelbonBevestigen
 */
@WebServlet("/orders/bevestigen.htm")
public class BestelbonBevestigen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/bevestiging.jsp";
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("image_path", "../images");
		request.setAttribute("landen", landService.findAll());
		request.setAttribute("bestelbonid", request.getParameter("bestelbonid"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
