package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.utente.Utente;

@WebServlet("/PrepareInsertCategoriaServlet")
public class PrepareInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertCategoriaServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Convalida utente altrimenti rimanda al login
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		if (utente == null || utente.getRuolo().equals("guest")) {
			session.invalidate();
			request.setAttribute("errorMessage", "Attenzione non hai i permessi per visualizzare questa pagina!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		// --fine convalida--

		request.getRequestDispatcher("insertCategoria.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
