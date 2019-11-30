package com.zaroumia.batch.validators;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class MyJobParametersValidator implements JobParametersValidator {

	@Override
	public void validate(final JobParameters jobParameters) throws JobParametersInvalidException {
		if (!StringUtils.endsWithIgnoreCase(jobParameters.getString("formateursFile"), "csv")) {
			throw new JobParametersInvalidException("le fichier des formateurs doit être au format csv");
		}

		if (!StringUtils.endsWithIgnoreCase(jobParameters.getString("formationsFile"), "xml")) {
			throw new JobParametersInvalidException("le fichier des formations doit être au format XML");
		}

		if (!StringUtils.endsWithIgnoreCase(jobParameters.getString("seancesFile"), "csv")
				&& !StringUtils.endsWithIgnoreCase(jobParameters.getString("seancesFile"), "txt")) {
			throw new JobParametersInvalidException("le fichier des séances doit être au format CSV ou TXT");
		}
	}

}
