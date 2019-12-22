package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.zaroumia.batch.dao.FormationDao;

public class ChargementFormationsStepConfigTest extends BaseTest {

	@Autowired
	private FormationDao formationDao;

	@Test
	public void shouldLoadFormationsWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formationsFile", "classpath:inputs/formationsFile.xml")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementFormationsStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		assertThat(formationDao.count()).isEqualTo(4);
	}

}
