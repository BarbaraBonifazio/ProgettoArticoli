package it.gestionearticoli.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.utente.Utente;
import it.gestionearticoli.service.utente.UtenteService;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usernameInputParam = request.getParameter("username");
		String passwordInputParam = request.getParameter("password");
		//fai controllo sui parametri 

		// se la validazione fallisce torno in pagina

		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
		try {
			Utente utente = null;
			utente = utenteService.trovaUsernamePassword(usernameInputParam, passwordInputParam);
				 
			if (utente != null) {
				HttpSession session = request.getSession();
				session.setAttribute("utente", utente);
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("errorMessage", "Attenzione! Le tue credenziali non sono valide!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
