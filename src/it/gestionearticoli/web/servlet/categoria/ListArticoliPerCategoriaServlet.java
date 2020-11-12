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

@WebServlet("/ListArticoliPerCategoriaServlet")
public class ListArticoliPerCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListArticoliPerCategoriaServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		if (utente == null) {
			session.invalidate();
			request.setAttribute("errorMessage", "Attenzione per visualizzare la pagina devi effettuare il login!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		// --fine convalida--

		String idCat = request.getParameter("idPerFindByCategoria");
		
		// Valido eventuale parametro passato da url
		if (idCat.isEmpty() || idCat == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--

		CategoriaService serviceCat = MyServiceFactory.getCategoriaServiceInstance();

		Categoria resultCat;

		try {
			resultCat = serviceCat.findById(Long.parseLong(idCat));
			
			// Verifico reale esistenza del parametro nel DB
			if (resultCat == null) {
				request.setAttribute("errorMessage", "Attenzione il valore inserito non esiste!");
				request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
				return;
			}
			// --fine verifica parametro DB
			
			resultCat.setListaArticoli(serviceCat.trovaPerCategoria(resultCat));
			request.setAttribute("categoriaPerResultsListArtPerCat", resultCat);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("resultsListArticoliPerCategoria.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
