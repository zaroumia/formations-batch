package com.zaroumia.batch.validators.deciders;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.util.StringUtils;

public class SeancesStepDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(final JobExecution jobExecution, final StepExecution stepExecution) {
		if (StringUtils.endsWithIgnoreCase(jobExecution.getJobParameters().getString("seancesFile"), "txt")) {
			return new FlowExecutionStatus("txt");
		} else {
			return new FlowExecutionStatus("csv");
		}
	}

}
