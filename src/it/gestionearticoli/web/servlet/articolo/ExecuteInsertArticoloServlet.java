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

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente)session.getAttribute("utente");
		if (	utente == null ||
				utente.getRuolo().equals("guest"))
				{
				session.invalidate();
				request.setAttribute("errorMessage", "Attenzione non hai i permessi per visualizzare questa pagina!");
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
				} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// validiamo input
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String idCatInputStringParam = request.getParameter("idDaInviareAExecuteInsert");

		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		// se la validazione fallisce torno in pagina
		
		try {
			
			if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1
					|| idCatInputStringParam.isEmpty()) {
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().listAll());
				request.getRequestDispatcher("insertArticolo.jsp").forward(request, response);
				return;
			}

			Categoria cat = new Categoria();
			cat.setIdCategoria(Long.parseLong(idCatInputStringParam));

			Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, cat);

			MyServiceFactory.getArticoloServiceInstance().inserisci(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());

			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListArticoli.jsp").forward(request, response);

	}

}
