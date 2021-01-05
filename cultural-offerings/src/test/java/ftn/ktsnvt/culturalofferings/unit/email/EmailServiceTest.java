package ftn.ktsnvt.culturalofferings.unit.email;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ftn.ktsnvt.culturalofferings.service.EmailServiceImpl;
import static ftn.ktsnvt.culturalofferings.unit.email.EmailConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class EmailServiceTest {
	
	
	
	@Autowired
	EmailServiceImpl emailService;
	
	@MockBean
	JavaMailSender mailSender;
	
	@Test
	public void sendEmailTestAllArgs() throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage(); 
		
        message.setTo(EMAIL_TO); 
        message.setSubject(SUBJECT); 
        message.setText(TEXT);
        doNothing().when(mailSender).send(message);
		emailService.sendMail(EMAIL_TO, SUBJECT, TEXT);
		
		verify(mailSender, timeout(1000).times(1)).send(Mockito.any(SimpleMailMessage.class));
	}
}
