package it.gestionearticoli.service.articolo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.model.articolo.Articolo;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDao;

	public void setArticoloDao(ArticoloDAO articoloDao) {
		this.articoloDao = articoloDao;
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		List<Articolo> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			articoloDao.setConnection(connection);

			// eseguo quello che realmente devo fare

			result = articoloDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo findById(Long idInput) throws Exception {

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			articoloDao.setConnection(connection);

			return articoloDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Articolo();
	}

	@Override
	public int aggiorna(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			articoloDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = articoloDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisci(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			articoloDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = articoloDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			articoloDao.setConnection(connection);

			// eseguo quello che realmente devo fare

			return articoloDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		List<Articolo> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			articoloDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = articoloDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	
}
