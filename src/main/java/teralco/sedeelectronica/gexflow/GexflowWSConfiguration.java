package teralco.sedeelectronica.gexflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class GexflowWSConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("gexflow.wsdl");
		return marshaller;
	}

	@Bean
	public GexflowClient quoteClient(Jaxb2Marshaller marshaller) {
		GexflowClient client = new GexflowClient();
		client.setDefaultUri("http://demo.gexflow.com:8080/gexflowzk/ws/CatalogoTramites");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
