package com.zaroumia.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public JobParametersValidator defaultJobParametersValidator() {
		DefaultJobParametersValidator bean = new DefaultJobParametersValidator();
		bean.setRequiredKeys(new String[] { "formateursFile", "formationsFile", "seancesFile" });
		return bean;
	}

	@Bean
	public Step step1(final StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(final StepContribution arg0, final ChunkContext arg1) throws Exception {
						System.out.println("Hello world");
						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}

	@Bean
	public Job job(final JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory.get("formations-batch")
				.start(step1(null))
				.validator(defaultJobParametersValidator())
				.build();
	}

}
