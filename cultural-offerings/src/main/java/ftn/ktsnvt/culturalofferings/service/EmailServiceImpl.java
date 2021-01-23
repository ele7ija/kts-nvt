package ftn.ktsnvt.culturalofferings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;

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
	/*
	@Async
	public void sendNewsLetter(News newsletter, String[] recipients, List<ImageModel> images) {
		MimeMessage message = javaMailSender.createMimeMessage();
	    
	    try {
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
			//String[] recipients = this.getRecipients(newsletter.getCulturalOffering().getId());
		    helper.setFrom("ivanin.office@gmail.com");
			helper.setTo(recipients);
			helper.setSubject(newsletter.getTitle());
		    helper.setText(newsletter.getText());
		    
		    //add images to email
		    for(ImageModel img : images) {
			    helper.addAttachment(img.getName(), new ByteArrayResource(imageService.decompressBytes(img.getPicByte())));

		    	/*
	            DataSource dataSource = new ByteArrayDataSource(imageService.decompressBytes(img.getPicByte()), "MIME");
			    helper.addAttachment(img.getName(), dataSource);
			    System.out.println("data source");*/
		    /*	
		    }
		    
		    javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    
	}*/
	
	@Async
	public void sendNewsLetter(News newsletter, String[] recipients, List<ImageModel> images) {
		 //String[] recipients = culturalOfferingService.getNewsletterRecipients(newsletter.getCulturalOffering().getId());
		 
		javaMailSender.send(new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom("ivanin.office@gmail.com");
			     message.setTo(recipients);
			     message.setSubject("my subject");
			     
				 String msgtext = newsletter.getText() + "\n";
				 /*
			     for(ImageModel img : images) {
				     message.addInline(img.getName(), new ByteArrayDataSource(imageService.decompressBytes(img.getPicByte()));
				     msgtext += "<img src='cid:" + img.getName() + "'>";
			     }*/
			     message.setText(msgtext, true);

			   }
			 });
	}
	
	
}
