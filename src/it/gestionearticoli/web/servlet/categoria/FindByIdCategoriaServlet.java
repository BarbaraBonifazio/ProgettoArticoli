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

@WebServlet("/FindByIdCategoriaServlet")
public class FindByIdCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindByIdCategoriaServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		if (utente == null) {
			session.invalidate();
			request.setAttribute("errorMessage", "Attenzione non hai i permessi per visualizzare questa pagina!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		// --fine convalida--

		String idCatDettaglio = request.getParameter("idPerFindByIdCat");
		
		// Valido eventuale parametro passato da url
		if (idCatDettaglio.isEmpty() || idCatDettaglio == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--
		
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();

		Categoria result;
		try {
			result = service.findById(Long.parseLong(idCatDettaglio));
			request.setAttribute("categoriaPerShow", result);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			
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

		request.getRequestDispatcher("listCategorieServlet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
