package com.zaroumia.batch.services;

import java.io.IOException;
import java.io.StringWriter;

import com.zaroumia.batch.domaine.Planning;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class MailContentGeneratorImpl implements MailContentGenerator {

	private final Template template;

	public MailContentGeneratorImpl(final Configuration conf)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		super();
		template = conf.getTemplate("planning.ftl");
	}

	@Override
	public String generate(final Planning planning) throws TemplateException, IOException {
		StringWriter sw = new StringWriter();
		template.process(planning, sw);
		return sw.toString();
	}

}
