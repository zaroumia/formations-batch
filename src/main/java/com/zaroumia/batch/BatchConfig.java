package com.zaroumia.batch;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaroumia.batch.validators.MyJobParametersValidator;
import com.zaroumia.batch.validators.deciders.SeancesStepDecider;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public JobParametersValidator defaultJobParametersValidator() {
		DefaultJobParametersValidator bean = new DefaultJobParametersValidator();
		bean.setRequiredKeys(new String[] { "formateursFile", "formationsFile", "seancesFile" });
		bean.setOptionalKeys(new String[] { "run.id" });
		return bean;
	}

	@Bean
	public JobParametersValidator myJobParametersValidator() {
		return new MyJobParametersValidator();
	}

	@Bean
	public JobParametersValidator compositeJobParametersValidator() {
		CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
		bean.setValidators(Arrays.asList(defaultJobParametersValidator(), myJobParametersValidator()));
		return bean;
	}

	@Bean
	public JobExecutionDecider seancesStepDecider() {
		return new SeancesStepDecider();
	}

	@Bean
	public Job job(final JobBuilderFactory jobBuilderFactory, final Step chargementFormateursStep,
			final Step chargementFormationsStep, final Step chargementSeancesCsvStep,
			final Step chargementSeancesTxtStep, final Step planningStep) {
		return jobBuilderFactory.get("formations-batch")
				.start(chargementFormateursStep)
				.next(chargementFormationsStep)
				.next(seancesStepDecider())
				.from(seancesStepDecider()).on("txt").to(chargementSeancesTxtStep)
				.from(seancesStepDecider()).on("csv").to(chargementSeancesCsvStep)
				.from(chargementSeancesTxtStep).on("*").to(planningStep)
				.from(chargementSeancesCsvStep).on("*").to(planningStep)
				.end()
				.validator(compositeJobParametersValidator())
				.incrementer(new RunIdIncrementer())
				.build();
	}

}
