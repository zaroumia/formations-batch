package com.zaroumia.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.zaroumia.batch.doamine.Formateur;

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
	public ItemWriter<Formateur> formateurItemWriter() {
		return (items) -> items.forEach(System.out::println);
	}

	@Bean
	public Step chargementFormateursStep(final StepBuilderFactory builderFactory) {
		return builderFactory.get("chargementFormateursStep")
				.<Formateur, Formateur>chunk(10)
				.reader(formateurItemReader(null))
				.writer(formateurItemWriter())
				.build();
	}
}
