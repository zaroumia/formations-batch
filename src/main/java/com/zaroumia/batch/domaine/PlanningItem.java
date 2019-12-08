package com.zaroumia.batch.domaine;

import java.time.LocalDate;

public class PlanningItem {

	private String libelleFormation;
	private String descriptifFormation;
	private LocalDate dateDebutSeance;
	private LocalDate dateFinSeance;

	public String getLibelleFormation() {
		return libelleFormation;
	}

	public void setLibelleFormation(final String libelleFormation) {
		this.libelleFormation = libelleFormation;
	}

	public String getDescriptifFormation() {
		return descriptifFormation;
	}

	public void setDescriptifFormation(final String descriptifFormation) {
		this.descriptifFormation = descriptifFormation;
	}

	public LocalDate getDateDebutSeance() {
		return dateDebutSeance;
	}

	public void setDateDebutSeance(final LocalDate dateDebutSeance) {
		this.dateDebutSeance = dateDebutSeance;
	}

	public LocalDate getDateFinSeance() {
		return dateFinSeance;
	}

	public void setDateFinSeance(final LocalDate dateFinSeance) {
		this.dateFinSeance = dateFinSeance;
	}

}
