package it.gestionearticoli.dao.categoria;

import java.util.List;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.articolo.Articolo;
import it.gestionearticoli.model.categoria.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	public List<Articolo> findByCategoria(Categoria categoria) throws Exception;
}
