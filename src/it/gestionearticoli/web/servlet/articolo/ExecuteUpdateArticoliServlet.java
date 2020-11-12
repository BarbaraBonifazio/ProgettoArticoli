package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.categoria.Categoria;
import it.gestionearticoli.model.utente.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

@WebServlet("/ExecuteUpdateArticoliServlet")
public class ExecuteUpdateArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateArticoliServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		if (utente == null || utente.getRuolo().equals("guest")) {
			session.invalidate();
			request.setAttribute("errorMessage", "Attenzione non hai i permessi per visualizzare questa pagina!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdDellArticoloCheVoglioModificare = request.getParameter("idarticoloPerUpdate");
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String idCatInputStringParam = request.getParameter("idDaInviareAExecuteUpdate");
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;

		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("updateArticolo.jsp").forward(request, response);
			return;
		}

		// occupiamoci delle operazioni di business
		Categoria cat = new Categoria();
		cat.setIdCategoria(Long.parseLong(idCatInputStringParam));

		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, cat);
		articoloInstance.setId(Long.parseLong(parametroIdDellArticoloCheVoglioModificare));

		try {

			service.aggiorna(articoloInstance);
			request.setAttribute("listaArticoliAttribute", service.listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		
		request.getRequestDispatcher("resultsListArticoli.jsp").forward(request, response);

	}
}
