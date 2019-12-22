package com.zaroumia.batch.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FormationDaoImpl implements FormationDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from formations", Integer.class);
	}

}
