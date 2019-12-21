package com.zaroumia.batch;

import org.junit.runner.RunWith;
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
public abstract class BaseTest {

	@Autowired
	protected JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@MockBean
	protected PlanningMailSenderService planningMailSenderService;

	@MockBean
	protected MailContentGenerator mailContentGenerator;
}
