package com.zaroumia.batch;

import static org.assertj.core.api.Assertions.assertThat;

import javax.mail.MessagingException;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.test.context.jdbc.Sql;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetup;

public class PlanningStepConfigTest extends BaseTest {

	@Rule
	public GreenMailRule serverSmtp = new GreenMailRule(new ServerSetup(2525, "localhost", ServerSetup.PROTOCOL_SMTP));

	@Test
	@Sql("classpath:init-all-tables.sql")
	public void shouldSendPlanningsWithSuccess() throws MessagingException {

		JobExecution result = jobLauncherTestUtils.launchStep("planningStep");

		assertThat(result.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
		assertThat(serverSmtp.getReceivedMessages()).hasSize(4);
		assertThat(serverSmtp.getReceivedMessages()[0].getSubject()).isEqualTo("Votre planning de formations");
	}

}
