package it.gestionearticoli.service.utente;

import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.utente.Utente;

public interface UtenteService {
	// questo mi serve per injection
	
	public Utente trovaUsernamePassword(String username, String password) throws Exception;

	public void setUtenteDao(UtenteDAO utenteDao);
}
