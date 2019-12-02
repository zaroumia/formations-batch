package com.zaroumia.batch.domaine;

import java.time.LocalDate;

public class Seance {

	private Integer idFormateur;
	private String codeFormation;
	private LocalDate dateDebut;
	private LocalDate dateFin;

	public Seance() {
		super();
	}

	public Seance(final Integer idFormateur, final String codeFormation, final LocalDate dateDebut,
			final LocalDate dateFin) {
		super();
		this.idFormateur = idFormateur;
		this.codeFormation = codeFormation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(final LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(final LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(final Integer idFormateur) {
		this.idFormateur = idFormateur;
	}

	public String getCodeFormation() {
		return codeFormation;
	}

	public void setCodeFormation(final String codeFormation) {
		this.codeFormation = codeFormation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seance [idFormateur=");
		builder.append(idFormateur);
		builder.append(", codeFormation=");
		builder.append(codeFormation);
		builder.append(", dateDebut=");
		builder.append(dateDebut);
		builder.append(", dateFin=");
		builder.append(dateFin);
		builder.append("]");
		return builder.toString();
	}

}
