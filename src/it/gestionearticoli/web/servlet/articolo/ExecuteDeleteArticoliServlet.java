package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.utente.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

@WebServlet("/ExecuteDeleteArticoliServlet")
public class ExecuteDeleteArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteArticoliServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		if (utente == null || utente.getRuolo().equals("guest") || utente.getRuolo().equals("operator")) {
			session.invalidate();
			request.setAttribute("errorMessage", "Attenzione non hai i permessi per visualizzare questa pagina!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

		String parametroIdDellArticoloCheVoglioCancellare = request.getParameter("idDaInviareAExecuteDelete");

		// Valido eventuale parametro passato da url
		if (parametroIdDellArticoloCheVoglioCancellare.isEmpty()
				|| parametroIdDellArticoloCheVoglioCancellare == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--

		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();

		Articolo result;

		try {
			result = service.findById(Long.parseLong(parametroIdDellArticoloCheVoglioCancellare));
			service.rimuovi(result);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

			// Verifico reale esistenza del parametro nel DB
			if (result == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("resultsListArticoli.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
