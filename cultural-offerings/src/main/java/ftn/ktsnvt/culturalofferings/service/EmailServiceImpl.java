package ftn.ktsnvt.culturalofferings.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.News;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	ImageService imageService;

	@Async
	public void sendMail(String toEmail, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom("ivanin.office@gmail.com");
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);

		javaMailSender.send(simpleMailMessage);
	}

	@Async
	public void sendNewsLetter(News newsletter, String[] recipients, List<ImageModel> images) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			// String[] recipients =
			// this.getRecipients(newsletter.getCulturalOffering().getId());
			helper.setTo(recipients);
			helper.setSubject(newsletter.getTitle());
			helper.setText(newsletter.getText());

			// add images to email
			for (ImageModel img : images) {
				helper.addAttachment(img.getName(),
						new ByteArrayResource(imageService.decompressBytes(img.getPicByte())));
			}
			System.out.println("Usao");
			javaMailSender.send(message);
		} catch (MessagingException e) {
			System.out.println("Nije");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
