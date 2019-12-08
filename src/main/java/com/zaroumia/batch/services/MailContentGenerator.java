package com.zaroumia.batch.services;

import java.io.IOException;

import com.zaroumia.batch.domaine.Planning;

import freemarker.template.TemplateException;

public interface MailContentGenerator {

	String generate(Planning planning) throws TemplateException, IOException;
}
