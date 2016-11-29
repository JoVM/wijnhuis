package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Soort;
import be.vdab.services.LandService;
import be.vdab.services.SoortService;

/**
 * Servlet implementation class PerSoort
 */
@WebServlet("/landen/persoort.htm")
public class PerSoort extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten/persoort.jsp";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("image_path", "../images");
		request.setAttribute("landen", landService.findAll());
		String idSoort = request.getParameter("idsoort");
		if (idSoort != null) {
			try {
				Soort soort = soortService.read(Long.parseLong(idSoort));
				request.setAttribute("soort", soort);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
