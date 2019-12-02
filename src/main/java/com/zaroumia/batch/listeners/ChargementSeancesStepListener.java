package com.zaroumia.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.StepListenerSupport;

import com.zaroumia.batch.domaine.Seance;

public class ChargementSeancesStepListener extends StepListenerSupport<Seance, Seance>
		implements StepExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargementSeancesStepListener.class);

	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		LOGGER.info("Chargement des seances :{} seance(s) enregistrée(s) ", stepExecution.getWriteCount());
		return stepExecution.getExitStatus();
	}

}
