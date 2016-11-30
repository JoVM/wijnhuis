package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;
import be.vdab.enums.Bestelwijze;
import be.vdab.services.BestelbonService;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.BestelbonLijn;

/**
 * Servlet implementation class Mandje
 */
@WebServlet("/orders/mandje.htm")
public class Mandje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/mandje.jsp";
	private static final String MANDJE = "mandje";
	private static final String REDIRECT_URL = "%s/orders/bevestigen.htm";
	private final transient LandService landService = new LandService();
	private final transient WijnService wijnService = new WijnService();
	private final transient BestelbonService bestelbonservice = new BestelbonService();

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
			session.removeAttribute("mandjefoto");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bestelwijze = 0;
		Set<BestelbonLijn> bestelbonlijnen = new HashSet<>();
		request.setAttribute("image_path", "../images");
		request.setAttribute("landen", landService.findAll());
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				Map<Wijn, Integer> wijnenInMandje = new HashMap<>();
				for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
					Wijn wijn = wijnService.read(entry.getKey());
					wijnenInMandje.put(wijn, entry.getValue());
					bestelbonlijnen.add(new BestelbonLijn(entry.getValue(),
							wijn.getPrijs().multiply(new BigDecimal(entry.getValue())), wijn));
				}
				request.setAttribute("wijnenInMandje", wijnenInMandje);
			}
			Map<String, String> fouten = new HashMap<>();
			String naam = request.getParameter("naam");
			if (!Bestelbon.isNaamValid(naam)) {
				fouten.put("naam", "verplicht");
			}
			String gemeente = request.getParameter("gemeente");
			if (!Adres.isGemeenteValid(gemeente)) {
				fouten.put("gemeente", "verplicht");
			}
			String huisNr = request.getParameter("huisnummer");
			if (!Adres.isGemeenteValid(huisNr)) {
				fouten.put("huisnummer", "verplicht");
			}
			String straat = request.getParameter("straat");
			if (!Adres.isGemeenteValid(straat)) {
				fouten.put("straat", "verplicht");
			}
			String postcode = request.getParameter("postcode");
			if (!Adres.isGemeenteValid(postcode)) {
				fouten.put("postcode", "verplicht");
			}
			String leverwijze = request.getParameter("leverwijze");
			if (leverwijze == null) {
				fouten.put("leverwijze", "maak een keuze");
			} else {
				switch (leverwijze) {
				case "afhalen":
					bestelwijze = 0;
					break;
				case "opsturen":
					bestelwijze = 1;
					break;
				default:
					fouten.put("leverwijze", "maak een keuze");
				}
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			} else {
				Adres nieuwAdres = new Adres(gemeente, huisNr, postcode, straat);
				Bestelbon nieuweBestelbon = null;
				if (bestelwijze == 0) {
					nieuweBestelbon = new Bestelbon(new Date(Calendar.getInstance().getTimeInMillis()),
							Bestelwijze.AFHALEN, naam, 0, nieuwAdres);
				} else if (bestelwijze == 1) {
					nieuweBestelbon = new Bestelbon(new Date(Calendar.getInstance().getTimeInMillis()),
							Bestelwijze.LEVEREN, naam, 0, nieuwAdres);
				}
				for (BestelbonLijn bestelbonlijn : bestelbonlijnen) {
					nieuweBestelbon.addBestelbonLijn(bestelbonlijn);
				}
				try {
					bestelbonservice.create(nieuweBestelbon);
				} catch (Exception e) {
					request.setAttribute("fouten", fouten);
					request.getRequestDispatcher(VIEW).forward(request, response);
				}
				response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
			}
		}
	}

}
