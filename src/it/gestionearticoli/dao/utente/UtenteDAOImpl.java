package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.utente.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO implements UtenteDAO {

	public Utente findByUsernameAndPassword(String username, String password) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ResultSet resultSet = null;
		Utente utenteResult = null;

		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM utente WHERE username = ? AND password = ?");

		) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				utenteResult = new Utente();
				utenteResult.setIdUtente(resultSet.getLong("id_utente"));
				utenteResult.setNome(resultSet.getString("nome"));
				utenteResult.setCognome(resultSet.getString("cognome"));
				utenteResult.setCodiceFiscale(resultSet.getString("codice_fiscale"));
				utenteResult.setUsername(resultSet.getString("username"));
				utenteResult.setPassword(resultSet.getString("password"));
				utenteResult.setRuolo(resultSet.getString("ruolo"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenteResult;

	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
