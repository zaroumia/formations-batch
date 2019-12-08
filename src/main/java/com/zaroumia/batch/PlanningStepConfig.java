package com.zaroumia.batch;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaroumia.batch.domaine.Planning;
import com.zaroumia.batch.mappers.PlanningRowMapper;
import com.zaroumia.batch.services.MailContentGenerator;
import com.zaroumia.batch.services.MailContentGeneratorImpl;
import com.zaroumia.batch.validators.PlanningProcessor;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

@Configuration
public class PlanningStepConfig {

	@Bean
	public JdbcCursorItemReader<Planning> planningItemReader(final DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Planning>()
				.name("planningItemReader")
				.dataSource(dataSource)
				.sql("select distinct f.* from formateurs f join seances s on f.id=s.id_formateur")
				.rowMapper(new PlanningRowMapper())
				.build();
	}

	@Bean
	public ItemProcessor<Planning, Planning> planningProcessor(final NamedParameterJdbcTemplate jdbcTemplate) {
		return new PlanningProcessor(jdbcTemplate);
	}

	@Bean
	public MailContentGenerator mailContentGenerator(final freemarker.template.Configuration conf)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		return new MailContentGeneratorImpl(conf);
	}
}
