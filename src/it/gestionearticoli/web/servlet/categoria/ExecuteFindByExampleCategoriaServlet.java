package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/ExecuteFindByExampleCategoriaServlet")
public class ExecuteFindByExampleCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExecuteFindByExampleCategoriaServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descCat = request.getParameter("descrizioneCat");

		// occupiamoci delle operazioni di business
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria categoriaInstance = new Categoria(descCat);
		

		try {
			List<Categoria> listaCat = service.findByExample(categoriaInstance);			
			request.setAttribute("listaCatPerSearch", listaCat);

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsSearchCategoria.jsp").forward(request, response);

	}
		
	

}
