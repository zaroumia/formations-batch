package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.zaroumia.batch.dao.FormateurDao;
import com.zaroumia.batch.dao.FormationDao;
import com.zaroumia.batch.dao.SeanceDao;

public class BtachConfigTest extends BaseTest {

	@Autowired
	private FormateurDao formateurDao;
	@Autowired
	private FormationDao formationDao;
	@Autowired
	private SeanceDao seanceDao;

	@Test
	public void shouldExecuteJobWithSuccess() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("formateursFile", "classpath:inputs/formateursFile.csv")
				.addString("formationsFile", "classpath:inputs/formationsFile.xml")
				.addString("seancesFile", "classpath:inputs/seancesFile.csv")
				.toJobParameters();

		JobExecution result = jobLauncherTestUtils.launchJob(jobParameters);

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
		assertThat(formateurDao.count()).isEqualTo(16);
		assertThat(formationDao.count()).isEqualTo(4);
		assertThat(seanceDao.count()).isEqualTo(20);
		Mockito.verify(planningMailSenderService, Mockito.times(4)).send(ArgumentMatchers.any(),
				ArgumentMatchers.any());
	}

}
