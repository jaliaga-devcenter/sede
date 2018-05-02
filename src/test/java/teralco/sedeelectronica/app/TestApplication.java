package teralco.sedeelectronica.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@EnableAutoConfiguration
@ComponentScan(basePackages = "teralco.sedeelectronica")
public class TestApplication {

	public static void main(String[] args) {
	}

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes();
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setProtocol("SMTP");
		javaMailSender.setHost("127.0.0.1");
		javaMailSender.setPort(25);
		return javaMailSender;
	}

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}

}