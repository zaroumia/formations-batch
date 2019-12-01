package com.zaroumia.batch.domaine;

public class Formateur {

	private Integer id;
	private String nom;
	private String prenom;
	private String adresseEmail;

	public Formateur(final Integer id, final String nom, final String prenom,
			final String adresseEmail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseEmail = adresseEmail;
	}

	public Formateur() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public String getAdresseEmail() {
		return adresseEmail;
	}

	public void setAdresseEmail(final String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Formateur [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", adresseEmail=");
		builder.append(adresseEmail);
		builder.append("]");
		return builder.toString();
	}
}
