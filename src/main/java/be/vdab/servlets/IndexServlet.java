package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.LandService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("image_path", "images");
		request.setAttribute("landen", landService.findAll());
		HttpSession session = request.getSession(false);
		if (session != null) {
			String mandjefoto = (String) session.getAttribute("mandjefoto");
			if (mandjefoto != null) {
				request.setAttribute("mandjefoto", mandjefoto);
			}
			session.removeAttribute("mandjefoto");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
