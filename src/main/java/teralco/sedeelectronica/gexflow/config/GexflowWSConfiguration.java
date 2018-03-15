package teralco.sedeelectronica.gexflow.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import teralco.sedeelectronica.gexflow.client.GexflowClient;

@Configuration
public class GexflowWSConfiguration {

	@Value("${sede.ws.tramites}")
	private String wsTramites;

	@Bean(name = "gexflowMarshaller")
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("gexflow.wsdl");
		marshaller.setMtomEnabled(true);
		return marshaller;
	}

	@Bean
	public GexflowClient quoteClient(@Qualifier("gexflowMarshaller") Jaxb2Marshaller marshaller) {
		GexflowClient client = new GexflowClient();
		client.setDefaultUri(this.wsTramites);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
