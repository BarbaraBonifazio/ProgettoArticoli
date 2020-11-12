package it.gestionearticoli.dao.utente;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.utente.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	public Utente findByUsernameAndPassword(String username, String password) throws Exception;

}
