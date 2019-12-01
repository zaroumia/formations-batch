package com.zaroumia.batch;

import static com.zaroumia.batch.mappers.FormateurItemPreparedStatementSetter.FORMATEURS_INSERT_QUERY;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.zaroumia.batch.doamine.Formateur;
import com.zaroumia.batch.mappers.FormateurItemPreparedStatementSetter;

@Configuration
public class ChargementFormateursStepConfig {

	@Bean
	@StepScope
	public FlatFileItemReader<Formateur> formateurItemReader(
			@Value("#{jobParameters['formateursFile']}") final Resource inputFile) {
		return new FlatFileItemReaderBuilder<Formateur>()
				.name("FormateurItemReader")
				.resource(inputFile)
				.delimited()
				.delimiter(";")
				.names(new String[] { "id", "nom", "prenom",
						"adresseEmail" })
				.targetType(Formateur.class)
				.build();
	}

	@Bean
	public JdbcBatchItemWriter<Formateur> formateurItemWriter(final DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Formateur>()
				.dataSource(dataSource)
				.sql(FORMATEURS_INSERT_QUERY)
				.itemPreparedStatementSetter(new FormateurItemPreparedStatementSetter())
				.build();
	}

	@Bean
	public Step chargementFormateursStep(final StepBuilderFactory builderFactory) {
		return builderFactory.get("chargementFormateursStep")
				.<Formateur, Formateur>chunk(10)
				.reader(formateurItemReader(null))
				.writer(formateurItemWriter(null))
				.build();
	}
}
