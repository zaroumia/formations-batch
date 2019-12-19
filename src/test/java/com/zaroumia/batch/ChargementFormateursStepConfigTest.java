package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zaroumia.batch.services.MailContentGenerator;
import com.zaroumia.batch.services.PlanningMailSenderService;

@RunWith(SpringRunner.class)
@JdbcTest
@SpringBatchTest
@ContextConfiguration(classes = ConfigurationForTest.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ChargementFormateursStepConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@MockBean
	private PlanningMailSenderService planningMailSenderService;

	@MockBean
	private MailContentGenerator mailContentGenerator;

	@Test
	public void shouldLoadFormateursWithSuccess() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formateursFile", "classpath:inputs/formateursFile.csv")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchStep("chargementFormateursStep", jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

		Integer count = jdbcTemplate.queryForObject("select count(*) from formateurs", Integer.class);

		assertThat(count).isEqualTo(16);
	}

}
