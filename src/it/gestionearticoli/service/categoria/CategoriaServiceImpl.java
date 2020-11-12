package it.gestionearticoli.service.categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.categoria.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDao;

	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		List<Categoria> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria findById(Long id) throws Exception {

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			categoriaDao.setConnection(connection);
			return categoriaDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Categoria();
	}

	@Override
	public int aggiorna(Categoria input) throws Exception {

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Categoria input) throws SQLException, Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare

			result = categoriaDao.delete(input);
			
		return result;
		}
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		List<Categoria> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Articolo> trovaPerCategoria(Categoria categoria) throws Exception {
		List<Articolo> result = new ArrayList<>();

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare

			result = categoriaDao.findByCategoria(categoria);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
//	public Categoria trovaDescrizione(Long id) throws Exception {
//
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			categoriaDao.setConnection(connection);
//
//			return categoriaDao.get(id);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return new Categoria();
//	}
	
//	@Override
//	public Categoria trovaByDescrizione(String descrizione) throws Exception {
//		
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			categoriaDao.setConnection(connection);
//
//			return categoriaDao.findByDescrizione(descrizione);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return new Categoria();
//	}
	

}
