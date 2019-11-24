package com.zaroumia.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FormationsBatchApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FormationsBatchApplication.class, args);
	}

}
