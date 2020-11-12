package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.categoria.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");

			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				categoriaTemp.setIdCategoria(rs.getLong("ID_CATEGORIA"));
				result.add(categoriaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria get(Long idCategoria) throws Exception {
		if (isNotActive()) {
			return null;
		}
		ResultSet rs = null;
		Categoria categoriaResult = null;

		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ?");

		) {

			preparedStatement.setLong(1, idCategoria);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				categoriaResult = new Categoria();
				categoriaResult.setDescrizione(rs.getString("DESCRIZIONE"));
				categoriaResult.setIdCategoria(rs.getLong("ID_CATEGORIA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return categoriaResult;
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive()) {
			return 0;
		}

		int result = 0;

		String query = "UPDATE categoria SET descrizione = ? WHERE id_categoria = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getDescrizione());
			ps.setLong(2, input.getIdCategoria());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Categoria input) throws Exception {

		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO categoria (descrizione) VALUES (?);",
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, input.getDescrizione());
			result = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				return rs.getInt(1); // 1 sta per il posizionamento della colonna all'interno del DB
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {

		if (isNotActive()) {
			return 0;
		}
		String query = "DELETE FROM categoria WHERE id_categoria = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getIdCategoria());
			return preparedStatement.executeUpdate();
		}
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;
		ResultSet rs = null;

		String query = "SELECT * FROM categoria WHERE (descrizione like ? OR ? = '')";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, '%'+input.getDescrizione()+'%');
			ps.setString(2, input.getDescrizione());
			rs = ps.executeQuery();

			if (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setDescrizione(rs.getString("descrizione"));
				result.add(categoriaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	public List<Articolo> findByCategoria(Categoria categoria) throws Exception {
		if (isNotActive()) {
			return null;
		}

		
		ArrayList<Articolo> result = new ArrayList<Articolo>();
		ResultSet rs = null;
		Articolo articoloTemp = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articolo A "
				+ " LEFT JOIN categoria C ON C.id_categoria = A.categoria_fk " + " WHERE C.descrizione = ?")) {

			preparedStatement.setString(1, categoria.getDescrizione());
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("A.DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				Long idCat = rs.getLong("CATEGORIA_FK");
				String descCat = rs.getString("C.DESCRIZIONE");
				Categoria cat = new Categoria();
				cat.setIdCategoria(idCat);
				cat.setDescrizione(descCat);
				if (descCat == null) {
					cat.setDescrizione("null");
				}
				articoloTemp.setCategoria(cat);

				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}	
	
	
//	public Categoria findDescrizione(Long idCategoria) throws Exception {
//	if (isNotActive()) {
//		return null;
//	}
//	ResultSet rs = null;
//	Categoria categoriaResult = null;
//
//	try (PreparedStatement preparedStatement = connection
//			.prepareStatement("SELECT descrizione FROM categoria WHERE id_categoria = ?");
//
//	) {
//
//		preparedStatement.setLong(1, idCategoria);
//		rs = preparedStatement.executeQuery();
//
//		if (rs.next()) {
//			categoriaResult = new Categoria();
//			categoriaResult.setDescrizione(rs.getString("DESCRIZIONE"));
//			categoriaResult.setIdCategoria(rs.getLong("ID_CATEGORIA"));
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//		throw e;
//	}
//	return categoriaResult;
//}	

//	@Override
//	public Categoria findByDescrizione(Long id, String descrizione) throws Exception {
//		if (isNotActive()) {
//			return null;
//		}
//		ResultSet rs = null;
//		Categoria categoriaResult = null;
//
//		try (PreparedStatement preparedStatement = connection
//				.prepareStatement("SELECT * FROM categoria WHERE id_categoria = ? OR ? '' AND descrizione = ? OR ? = ''");
//
//		) {
//			preparedStatement.setLong(1, Long.parseLong("%"+id+"%"));
//			preparedStatement.setString(2, "%"+descrizione+"%");
//			rs = preparedStatement.executeQuery();
//
//			if (rs.next()) {
//				categoriaResult = new Categoria();
//				categoriaResult.setDescrizione(rs.getString("DESCRIZIONE"));
//				categoriaResult.setIdCategoria(rs.getLong("ID_CATEGORIA"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return categoriaResult;
//	}

}
