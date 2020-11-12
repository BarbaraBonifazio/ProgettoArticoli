package it.gestionearticoli.model.utente;

public class Utente {

	private Long idUtente;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String username;
	private String password;
	private String ruolo;

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Utente() {
	}
	
	public Utente(Long idUtente, String nome, String cognome, String codiceFiscale, String username, String password,
			String ruolo) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Utente(String username, String password) {
		this.username = username;
		this.password = password;
	}


}
