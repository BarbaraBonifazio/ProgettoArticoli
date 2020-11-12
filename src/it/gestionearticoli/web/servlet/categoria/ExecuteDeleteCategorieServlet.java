package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/ExecuteDeleteCategorieServlet")
public class ExecuteDeleteCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteCategorieServlet() {
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
		// --fine convalida--

		String idCatDaCancellare = request.getParameter("idPerExecuteDeleteCat");
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria result = new Categoria();
		
		// Valido eventuale parametro passato da url
		if (idCatDaCancellare.isEmpty() || idCatDaCancellare == null) {
			request.setAttribute("errorMessage", "Attenzione il valore inserito non è valido!");
			request.getRequestDispatcher("resultsListCategorie.jsp").forward(request, response);
			return;
		}
		// --fine validazione parametro da url--

			try{
			    if(idCatDaCancellare != null && !idCatDaCancellare.isEmpty()){
			        result.setIdCategoria(Long.parseLong(idCatDaCancellare));
			        service.rimuovi(result);
			    } 
			} catch (SQLException e){
			    	request.setAttribute("errorMessage", "Attenzione! La categoria che stai tentando di eliminare contiene degli articoli!");
					request.getRequestDispatcher("ListCategorieServlet").forward(request, response);
			} catch (Exception e) {
					e.printStackTrace();
			 }
		
		request.getParameter("categoriaDaEliminare");
		response.sendRedirect(request.getContextPath()+"/ListCategorieServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
