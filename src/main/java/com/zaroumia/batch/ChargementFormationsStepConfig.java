package com.zaroumia.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.zaroumia.batch.domaine.Formation;

@Configuration
public class ChargementFormationsStepConfig {

	@Bean
	@StepScope
	public StaxEventItemReader<Formation> formationItemReader(
			@Value("#{jobParameters['formationsFile']}") final Resource inputFile) {
		return new StaxEventItemReaderBuilder().name("formationItemReader")
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
	public ItemWriter<Formation> formationItemWriter() {
		return (items) -> items.forEach(System.out::println);
	}

	@Bean
	public Step chargementFormationsStep(final StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("chargementFormationsStep")
				.<Formation, Formation>chunk(10)
				.reader(formationItemReader(null))
				.writer(formationItemWriter())
				.build();
	}
}
