package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import javax.mail.MessagingException;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.test.context.jdbc.Sql;

public class PlanningStepConfigTest extends BaseTest {

	@Test
	@Sql("classpath:init-all-tables.sql")
	public void shouldSendPlanningsWithSuccess() throws MessagingException {

		JobExecution result = jobLauncherTestUtils.launchStep("planningStep", new JobParameters());

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		Mockito.verify(planningMailSenderService, Mockito.times(4)).send(ArgumentMatchers.any(),
				ArgumentMatchers.any());
	}

}
