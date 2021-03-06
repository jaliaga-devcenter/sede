package teralco.sedeelectronica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import teralco.sedeelectronica.model.Contacto;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String springMailUsername;

	@Autowired
	public EmailService(JavaMailSender pJavaMailSender) {
		this.javaMailSender = pJavaMailSender;
	}

	@Async
	public void sendEmail(Contacto contacto) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(this.springMailUsername);
		mail.setFrom(contacto.getEmail());
		mail.setSubject("Formulario de contacto");
		mail.setText(contacto.getNombre() + "\n" + contacto.getEmail() + "\n" + contacto.getComentario());

		this.javaMailSender.send(mail);
	}

}