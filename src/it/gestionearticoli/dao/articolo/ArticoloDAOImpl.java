package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.categoria.Categoria;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(
					"SELECT * FROM articolo A " + " LEFT JOIN categoria C ON C.id_categoria = A.categoria_fk");

			while (rs.next()) {
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

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		ResultSet rs = null;
		Articolo articoloResult = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM articolo A "
				+ " LEFT JOIN categoria C ON C.id_categoria = A.categoria_fk " + " WHERE A.id = ?")) {

			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				articoloResult = new Articolo();
				articoloResult.setId(rs.getLong("ID"));
				articoloResult.setCodice(rs.getString("CODICE"));
				articoloResult.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloResult.setPrezzo(rs.getInt("PREZZO"));
				Long idCat = rs.getLong("CATEGORIA_FK");
				String descCat = rs.getString("C.DESCRIZIONE");
				Categoria cat = new Categoria();
				cat.setIdCategoria(idCat);
				cat.setDescrizione(descCat);
				if (descCat == null) {
					cat.setDescrizione("null");
				}
				articoloResult.setCategoria(cat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return articoloResult;

	}

	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive()) {
			return 0;
		}

		int result = 0;

		String query = "UPDATE articolo A SET A.codice = ?, A.descrizione = ?, A.prezzo = ?, A.categoria_fk = ? WHERE A.id = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getIdCategoria());
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO articolo (codice, descrizione, prezzo, categoria_fk) VALUES (?, ?, ?, ?)")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getIdCategoria());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive()) {
			return 0;

		}
		String query = "DELETE FROM articolo WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());

			return preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		Categoria categoriaTemp = null;
		ResultSet rs = null;

		String query = "SELECT * FROM articolo A "
				+ " JOIN categoria C ON A.categoria_fk = C.id_categoria "
				+ "WHERE (A.codice like ? OR ? = '') AND (A.descrizione like ? OR ? = '') "
				+ " AND (A.prezzo = ? OR ? = '')";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, '%'+input.getCodice()+'%');
			ps.setString(2, input.getCodice());
			ps.setString(3, '%'+input.getDescrizione()+'%');
			ps.setString(4, input.getDescrizione());
			ps.setInt(5, input.getPrezzo());
			ps.setInt(6, input.getPrezzo());

			rs = ps.executeQuery();

			if (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setId(rs.getLong("id"));
				articoloTemp.setCodice(rs.getString("codice"));
				articoloTemp.setDescrizione(rs.getString("A.descrizione"));
				articoloTemp.setPrezzo(rs.getInt("prezzo"));
				categoriaTemp = new Categoria();
				categoriaTemp.setIdCategoria(rs.getLong("categoria_fk"));
				categoriaTemp.setDescrizione(rs.getString("C.descrizione"));
				articoloTemp.setCategoria(categoriaTemp);
				result.add(articoloTemp);
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

}
