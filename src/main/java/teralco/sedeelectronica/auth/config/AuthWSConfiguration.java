package teralco.sedeelectronica.auth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import teralco.sedeelectronica.auth.client.AuthClient;

@Configuration
public class AuthWSConfiguration {

	@Value("${sede.ws.admin}")
	private String wsAdmin;

	@Bean(name = "adminMarshaller")
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("admin.wsdl");
		marshaller.setMtomEnabled(false);
		return marshaller;
	}

	/**
	 * @param marshaller
	 */
	@Bean
	public AuthClient adminClient(@Qualifier("adminMarshaller") Jaxb2Marshaller marshaller) {
		AuthClient client = new AuthClient(this.wsAdmin);
		client.setDefaultUri(this.wsAdmin);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
