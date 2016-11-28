package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Wijn;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class WijnToevoegen
 */
@WebServlet("/soorten/wijntoevoegen.htm")
public class WijnToevoegen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten/wijntoevoegen.jsp";
	private final transient LandService landService = new LandService();
	private final transient WijnService wijnService = new WijnService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("image_path", "../images");
		request.setAttribute("landen", landService.findAll());
		String idWijn = request.getParameter("idwijn");
		if (idWijn != null) {
//			try {
				Wijn wijn = wijnService.read(Long.parseLong(idWijn));
//				request.setAttribute("wijn", wijn);
			// } catch (NumberFormatException e) {
			//
			// }
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
