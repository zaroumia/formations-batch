package com.zaroumia.batch.mappers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.zaroumia.batch.domaine.Formateur;

public class FormateurItemPreparedStatementSetter implements ItemPreparedStatementSetter<Formateur> {

	public static final String FORMATEURS_INSERT_QUERY = "INSERT INTO formateurs(id,nom,prenom,adresse_email) VALUES (?,?,?,?);";

	@Override
	public void setValues(final Formateur formateur, final PreparedStatement ps) throws SQLException {
		ps.setInt(1, formateur.getId());
		ps.setString(2, formateur.getNom());
		ps.setString(3, formateur.getPrenom());
		ps.setString(4, formateur.getAdresseEmail());

	}

}
