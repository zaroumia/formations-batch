package com.zaroumia.batch.validators.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.zaroumia.batch.domaine.Planning;
import com.zaroumia.batch.services.MailContentGenerator;
import com.zaroumia.batch.services.PlanningMailSenderService;

public class PlanningItemWriter implements ItemWriter<Planning> {

	private final PlanningMailSenderService planningService;

	private final MailContentGenerator mailContentGenerator;

	public PlanningItemWriter(final PlanningMailSenderService planningService,
			final MailContentGenerator mailContentGenerator) {
		super();
		this.planningService = planningService;
		this.mailContentGenerator = mailContentGenerator;
	}

	@Override
	public void write(final List<? extends Planning> plannings) throws Exception {

		for (Planning planning : plannings) {
			String content = mailContentGenerator.generate(planning);
			planningService.send(planning.getFormateur().getAdresseEmail(), content);
		}

	}

}
