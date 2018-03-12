package teralco.sedeelectronica.pasarela.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import teralco.sedeelectronica.pasarela.client.PasarelaClient;

@Configuration
public class PasarelaWSConfiguration {

	@Value("${sede.ws.pasarela.url}")
	private String wsPasarela;

	@Value("${sede.ws.pasarela.usuario}")
	private String user;

	@Value("${sede.ws.pasarela.password}")
	private String password;

	@Bean(name = "pasarelaMarshaller")
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("pasarela.wsdl");
		marshaller.setMtomEnabled(false);
		return marshaller;
	}

	/**
	 * @param marshaller
	 */
	@Bean
	public PasarelaClient pasarelaClient(@Qualifier("pasarelaMarshaller") Jaxb2Marshaller marshaller) {
		PasarelaClient client = new PasarelaClient(this.wsPasarela);
		client.setDefaultUri(this.wsPasarela);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		ClientInterceptor[] interceptors = { wsseSecurityInterceptor() };
		client.setInterceptors(interceptors);
		return client;
	}

	@Bean
	public Wss4jSecurityInterceptor wsseSecurityInterceptor() {
		Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
		interceptor.setSecurementActions("UsernameToken");
		interceptor.setSecurementUsername(this.user);
		interceptor.setSecurementPassword(this.password);
		return interceptor;
	}
}
