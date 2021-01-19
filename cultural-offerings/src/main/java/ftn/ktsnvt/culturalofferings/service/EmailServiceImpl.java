package ftn.ktsnvt.culturalofferings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Subscription;

@Component
public class EmailServiceImpl {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Async
    public void sendMail(String toEmail, String subject, String message) {
    	
    	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);


        javaMailSender.send(simpleMailMessage);
    }
	
	@Async
	public void sendNewsLetter(News newsletter) {
		MimeMessage message = javaMailSender.createMimeMessage();
	    
	    try {
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
			String[] recipients = this.getRecipients(newsletter.getCulturalOffering().getSubscriptions());
			
			helper.setTo(recipients);
			helper.setSubject(newsletter.getTitle());
		    helper.setText(newsletter.getText());
		    
		    //add images to email
		    for(ImageModel img : newsletter.getImages()) {
	            DataSource dataSource = new ByteArrayDataSource(img.getPicByte(), "MIME");
			    helper.addAttachment(img.getName(), dataSource);
		    }
		    
		    javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	    
	}
	
	public String[] getRecipients(Set<Subscription> subscriptions) {
		List<String> list = new ArrayList<String>();
		
		for(Subscription s : subscriptions) {
			list.add(s.getUser().getEmail());
		}
		
		return list.toArray(String[]::new);
	}
	
	
	
}
