package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class BtachConfigTest extends BaseTest {

	@Test
	public void shouldExecuteJobWithSuccess() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formateursFile", "classpath:inputs/formateursFile.csv")
				.addString("formationsFile", "classpath:inputs/formationsFile.xml")
				.addString("seancesFile", "classpath:inputs/seancesFile.csv")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchJob(jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
		assertThat(jdbcTemplate.queryForObject("select count(*) from formateurs", Integer.class)).isEqualTo(16);
		assertThat(jdbcTemplate.queryForObject("select count(*) from formations", Integer.class)).isEqualTo(4);
		assertThat(jdbcTemplate.queryForObject("select count(*) from seances", Integer.class)).isEqualTo(20);
		Mockito.verify(planningMailSenderService, Mockito.times(4)).send(ArgumentMatchers.any(),
				ArgumentMatchers.any());
	}

}
