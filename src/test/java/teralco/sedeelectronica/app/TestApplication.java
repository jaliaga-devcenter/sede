package teralco.sedeelectronica.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import teralco.sedeelectronica.security.ProcedimientoHandlerInterceptorAdapter;

@Configuration
@ComponentScan(basePackages = "teralco.sedeelectronica")
@EntityScan("teralco.sedeelectronica.model")
@EnableJpaRepositories("teralco.sedeelectronica.repository")
public class TestApplication {

	public static void main(String[] args) {

	}

	@Bean
	public ProcedimientoHandlerInterceptorAdapter procedimientoInterceptor() {
		return new ProcedimientoHandlerInterceptorAdapter();
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