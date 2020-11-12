package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.utente.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;


@WebServlet("/ExecuteFindByExampleArticoloServlet")
public class ExecuteFindByExampleArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExecuteFindByExampleArticoloServlet() {
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
		
		String codArt = request.getParameter("codiceArt");
		String descArt = request.getParameter("descrArt");
		String prezArt = request.getParameter("prezzoArt");
		
		Integer prezzo = !prezArt.isEmpty() ? Integer.parseInt(prezArt) : 0;
		if (prezzo < 0) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione nel campo prezzo!");
			request.getRequestDispatcher("searchArticolo.jsp").forward(request, response);
			return;
		}
		
		// occupiamoci delle operazioni di business
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		Articolo articoloInstance = new Articolo(codArt, descArt, prezzo);

		try {
			
			
			List<Articolo> listaArt = service.findByExample(articoloInstance);			
			request.setAttribute("listaArtPerDesc", listaArt);

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		// andiamo ai risultati
		request.getRequestDispatcher("resultsSearchArticolo.jsp").forward(request, response);

		}
	}


