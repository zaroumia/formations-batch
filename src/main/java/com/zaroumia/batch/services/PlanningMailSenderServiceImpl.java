package com.zaroumia.batch.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class PlanningMailSenderServiceImpl implements PlanningMailSenderService {

	private final JavaMailSender javaMailSender;

	public PlanningMailSenderServiceImpl(final JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void send(final String destination, final String content) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		message.setContent(content, "text/html");
		helper.setTo(destination);
		helper.setSubject("Votre planning de formations");

		javaMailSender.send(message);
	}

}
