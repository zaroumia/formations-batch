package com.zaroumia.batch.mappers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.zaroumia.batch.domaine.Formation;

public class FormationItemPreparedStatementSetter implements ItemPreparedStatementSetter<Formation> {

	@Override
	public void setValues(final Formation item, final PreparedStatement ps) throws SQLException {
		ps.setString(1, item.getCode());
		ps.setString(2, item.getLibelle());
		ps.setString(3, item.getDescriptif());
	}

}
