package com.zaroumia.batch.domaine;

import java.util.List;

public class Planning {
	private Formateur formateur;
	private List<PlanningItem> seances;

	public List<PlanningItem> getSeances() {
		return seances;
	}

	public void setSeances(final List<PlanningItem> seances) {
		this.seances = seances;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(final Formateur formateur) {
		this.formateur = formateur;
	}
}
