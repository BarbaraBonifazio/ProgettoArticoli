package it.gestionearticoli.model.categoria;

import java.util.List;

import it.gestionearticoli.model.articolo.Articolo;

public class Categoria {

	private Long idCategoria;
	private String descrizione;
	private List<Articolo> listaArticoli;

	public Categoria() {
	}

	public Categoria(Long idCategoria, String descrizione) {
		this.idCategoria = idCategoria;
		this.descrizione = descrizione;
	}
	
	public Categoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public List<Articolo> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<Articolo> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}

	public String toString() {
		String s = descrizione;
		return s;
	}

}
