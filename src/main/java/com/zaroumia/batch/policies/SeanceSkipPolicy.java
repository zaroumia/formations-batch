package com.zaroumia.batch.policies;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.dao.DataIntegrityViolationException;

public class SeanceSkipPolicy implements SkipPolicy {

	@Override
	public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {

		if (t instanceof DataIntegrityViolationException && skipCount < 10) {
			return true;
		}
		return false;
	}

}
