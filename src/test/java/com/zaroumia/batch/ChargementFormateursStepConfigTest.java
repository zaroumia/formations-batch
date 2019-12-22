package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.zaroumia.batch.dao.FormateurDao;

public class ChargementFormateursStepConfigTest extends BaseTest {

	@Autowired
	private FormateurDao formateurDao;

	@Test
	public void shouldLoadFormateursWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formateursFile", "classpath:inputs/formateursFile.csv")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementFormateursStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		assertThat(formateurDao.count()).isEqualTo(16);
	}

}
