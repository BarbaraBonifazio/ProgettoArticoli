package it.gestionearticoli.service.utente;

import java.sql.Connection;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.utente.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDao;

	@Override
	public Utente trovaUsernamePassword(String username, String password) throws Exception {
		Utente utente = new Utente();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			utenteDao.setConnection(connection);

			utente = utenteDao.findByUsernameAndPassword(username, password);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return utente;
	}

	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;

	}

}
