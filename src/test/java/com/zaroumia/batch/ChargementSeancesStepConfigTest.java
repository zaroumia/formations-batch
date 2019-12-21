package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = { "classpath:init-formations-formateurs-tables.sql" })
public class ChargementSeancesStepConfigTest extends BaseTest {

	@Test
	public void shouldLoadSeancesCsvWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("seancesFile", "classpath:inputs/seancesFile.csv")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementSeancesCsvStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		Integer count = jdbcTemplate.queryForObject("select count(*) from seances", Integer.class);

		assertThat(count).isEqualTo(20);
	}

	@Test
	public void shouldLoadSeancesTxtWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("seancesFile", "classpath:inputs/seancesFile.txt")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementSeancesTxtStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		Integer count = jdbcTemplate.queryForObject("select count(*) from seances", Integer.class);

		assertThat(count).isEqualTo(20);
	}

}
