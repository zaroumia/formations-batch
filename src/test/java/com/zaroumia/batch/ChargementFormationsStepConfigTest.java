package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class ChargementFormationsStepConfigTest extends BaseTest {

	@Test
	public void shouldLoadFormationsWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formationsFile", "classpath:inputs/formationsFile.xml")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementFormationsStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		Integer count = jdbcTemplate.queryForObject("select count(*) from formations", Integer.class);

		assertThat(count).isEqualTo(4);
	}

}
