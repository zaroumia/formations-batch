package com.zaroumia.batch.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaroumia.batch.domaine.Planning;
import com.zaroumia.batch.domaine.PlanningItem;
import com.zaroumia.batch.mappers.PlanningItemRowMapper;

public class PlanningProcessor implements ItemProcessor<Planning, Planning> {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String QUERY = "select f.libelle, s.date_debut,s.date_fin"
			+ " from formations f join seances s on f.code=s.code_formation"
			+ " where s.id_formateur=:formateur"
			+ " order by s.date_debut ";

	public PlanningProcessor(final NamedParameterJdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Planning process(final Planning planning) throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("formateur", planning.getFormateur().getId());
		List<PlanningItem> items = jdbcTemplate.query(QUERY, params, new PlanningItemRowMapper());
		planning.setSeances(items);
		return planning;
	}

}
