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

@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteUpdateCategoriaServlet() {
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

		String idCatDaModificare = request.getParameter("idPerExecuteUpdateCat");
		String descrizioneInputParam = request.getParameter("descrizione");

		// se la validazione fallisce torno in pagina
		if (descrizioneInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("updateCategoria.jsp").forward(request, response);
			return;
		}

		// occupiamoci delle operazioni di business
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria categoriaInstance = new Categoria(descrizioneInputParam);
		categoriaInstance.setIdCategoria(Long.parseLong(idCatDaModificare));

		try {
			service.aggiorna(categoriaInstance);
			request.setAttribute("listaCategorieAttribute", service.listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// andiamo ai risultati
		request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);

	}
}
