package finaltp.mail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSeviceImpl implements MailService {

	private final JavaMailSender javaMailSender;
	
	@Autowired
	public MailSeviceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		// TODO Auto-generated constructor stub
	}
	
	public boolean send(String subject, String text, String from, String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject);
			helper.setText(text);
			helper.setFrom(from);
			helper.setTo(to);
			
			javaMailSender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
