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
 * Servlet implementation class WijnToevoegen
 */
@WebServlet("/soorten/wijntoevoegen.htm")
public class WijnToevoegen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten/wijntoevoegen.jsp";
	private static final String REDIRECT_URL = "%s/index.htm";
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
		String idWijn = request.getParameter("idwijn");
		if (idWijn != null) {
			try {
				Wijn wijn = wijnService.read(Long.parseLong(idWijn));
				request.setAttribute("wijn", wijn);
			} catch (NumberFormatException e) {

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
		Map<String, String> fouten = new HashMap<>();
		try {
			int aantal = new Integer(request.getParameter("aantal"));
			if (!Wijn.isAantalValid(aantal)) {
				fouten.put("aantal", "tik een positief getal");
			}
		} catch (NumberFormatException e) {
			fouten.put("aantal", "tik een positief getal");
		}
		if (!fouten.isEmpty()) {
			// ************** Put in session!!!!!!!!!!!!!!! *************
			request.setAttribute("image_path", "../images");
			request.setAttribute("landen", landService.findAll());
			String idWijn = request.getParameter("idwijn");
			if (idWijn != null) {
				try {
					Wijn wijn = wijnService.read(Long.parseLong(idWijn));
					request.setAttribute("wijn", wijn);
				} catch (NumberFormatException e) {

				}
			}
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje == null) {
				mandje = new HashMap<>();
			}
			try {
				if (mandje.containsKey(Long.parseLong(request.getParameter("idwijn")))) {
					int aantal = mandje.get(Long.parseLong(request.getParameter("idwijn")));
					mandje.put(Long.parseLong(request.getParameter("idwijn")),
							aantal + Integer.parseInt(request.getParameter("aantal")));
				} else {
					mandje.put(Long.parseLong(request.getParameter("idwijn")),
							Integer.parseInt(request.getParameter("aantal")));
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			session.setAttribute("mandjefoto", "mandjefoto");
			session.setAttribute(MANDJE, mandje);
			response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
		}
	}

}
