package com.zaroumia.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.StepListenerSupport;

import com.zaroumia.batch.doamine.Formateur;

public class ChargementFormateursStepListener extends StepListenerSupport<Formateur, Formateur>
		implements StepExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargementFormateursStepListener.class);

	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		LOGGER.info("Chargement des formateurs :{} formateur(s) enregistré(s) ", stepExecution.getReadCount());
		return stepExecution.getExitStatus();
	}

}
