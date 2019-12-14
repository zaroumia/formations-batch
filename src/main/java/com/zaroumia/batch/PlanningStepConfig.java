package com.zaroumia.batch;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import com.zaroumia.batch.domaine.Planning;
import com.zaroumia.batch.mappers.PlanningRowMapper;
import com.zaroumia.batch.services.MailContentGenerator;
import com.zaroumia.batch.services.MailContentGeneratorImpl;
import com.zaroumia.batch.services.PlanningMailSenderService;
import com.zaroumia.batch.services.PlanningMailSenderServiceImpl;
import com.zaroumia.batch.validators.PlanningProcessor;
import com.zaroumia.batch.validators.writers.PlanningItemWriter;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

@Configuration
public class PlanningStepConfig {

	@Bean(destroyMethod = "")
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

	@Bean
	public PlanningMailSenderService planningMailSenderService(final JavaMailSender javaMailSender) {
		return new PlanningMailSenderServiceImpl(javaMailSender);
	}

	@Bean
	public PlanningItemWriter planningWriter(final PlanningMailSenderService planningService,
			final MailContentGenerator mailContentGenerator) {
		return new PlanningItemWriter(planningService, mailContentGenerator);
	}

	@Bean
	public Step planningStep(final StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("planningStep")
				.<Planning, Planning>chunk(10)
				.reader(planningItemReader(null))
				.processor(planningProcessor(null))
				.writer(planningWriter(null, null))
				.build();
	}

}
