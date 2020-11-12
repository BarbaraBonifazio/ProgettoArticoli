package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.categoria.Categoria;
import it.gestionearticoli.model.utente.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

@WebServlet("/ConfirmDeleteCategoriaServlet")
public class ConfirmDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmDeleteCategoriaServlet() {
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
		//--fine validazione utente--

		String idCatDaEliminare = request.getParameter("idPerExecuteDeleteCat");
		
		// Valido eventuale parametro passato da url
		if (idCatDaEliminare.isEmpty() || idCatDaEliminare == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--

		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();

		try {
			Categoria categoriaInstance = service.findById(Long.parseLong(idCatDaEliminare));
			request.setAttribute("categoriaDaEliminare", categoriaInstance);
			
			// Verifico reale esistenza del parametro nel DB
			if (categoriaInstance == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("confermaEliminaCategoria.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
