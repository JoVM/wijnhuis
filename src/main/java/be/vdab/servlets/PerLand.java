package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Land;
import be.vdab.services.LandService;

/**
 * Servlet implementation class PerLand
 */
@WebServlet("/landen/perland.htm")
public class PerLand extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten/perland.jsp";
	private final transient LandService landService = new LandService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("image_path", "../images");
		request.setAttribute("landen", landService.findAll());
		String id = request.getParameter("id");
		if (id != null) {
			Land land = landService.read(Long.parseLong(id));
			request.setAttribute("land", land);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
