package com.zaroumia.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.zaroumia.batch.domaine.Formation;
import com.zaroumia.batch.listeners.ChargementFormationsStepListener;
import com.zaroumia.batch.mappers.FormationItemPreparedStatementSetter;

@Configuration
public class ChargementFormationsStepConfig {

	@Bean
	@StepScope
	public StaxEventItemReader<Formation> formationItemReader(
			@Value("#{jobParameters['formationsFile']}") final Resource inputFile) {
		return new StaxEventItemReaderBuilder<Formation>().name("formationItemReader")
				.resource(inputFile)
				.addFragmentRootElements("formation")
				.unmarshaller(formationMarshaller())
				.build();
	}

	@Bean
	public Jaxb2Marshaller formationMarshaller() {
		Jaxb2Marshaller bean = new Jaxb2Marshaller();
		bean.setClassesToBeBound(Formation.class);
		return bean;
	}

	@Bean
	public ItemWriter<Formation> formationItemWriter(final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Formation>()
				.dataSource(dataSource)
				.sql("INSERT INTO formations (code, libelle, descriptif) VALUES (?,?,?);")
				.itemPreparedStatementSetter(new FormationItemPreparedStatementSetter())
				.build();

	}

	@Bean
	public Step chargementFormationsStep(final StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("chargementFormationsStep")
				.<Formation, Formation>chunk(10)
				.reader(formationItemReader(null))
				.writer(formationItemWriter(null))
				.listener(chargementFormationsListener())
				.build();
	}

	@Bean
	public StepExecutionListener chargementFormationsListener() {
		return new ChargementFormationsStepListener();
	}
}
