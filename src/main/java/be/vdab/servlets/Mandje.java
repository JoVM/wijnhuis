package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijn;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class Mandje
 */
@WebServlet("/orders/mandje.htm")
public class Mandje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/mandje.jsp";
	private static final String MANDJE = "mandje";
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
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				Map<Wijn, Integer> wijnenInMandje = new HashMap<>();
				for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
					wijnenInMandje.put(wijnService.read(entry.getKey()), entry.getValue());
				}
				request.setAttribute("wijnenInMandje", wijnenInMandje);
			}
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
