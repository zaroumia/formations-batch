package com.zaroumia.batch.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zaroumia.batch.domaine.PlanningItem;

public class PlanningItemRowMapper implements RowMapper<PlanningItem> {

	@Override
	public PlanningItem mapRow(final ResultSet rs, final int num) throws SQLException {
		PlanningItem item = new PlanningItem();
		item.setLibelleFormation(rs.getString(1));
		item.setDateDebutSeance(rs.getDate(2).toLocalDate());
		item.setDateFinSeance(rs.getDate(3).toLocalDate());
		return item;
	}

}
