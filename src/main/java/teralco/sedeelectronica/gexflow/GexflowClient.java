package teralco.sedeelectronica.gexflow;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import gexflow.wsdl.ConsultaCatalogoCategorias;
import gexflow.wsdl.ConsultaCatalogoCategoriasResponse;
import gexflow.wsdl.ObjectFactory;

public class GexflowClient extends WebServiceGatewaySupport {

	private ObjectFactory factory = new ObjectFactory();

	public ConsultaCatalogoCategoriasResponse getCategorias(Integer entidad, String idioma) {

		ConsultaCatalogoCategorias request = new ConsultaCatalogoCategorias();
		request.setCodigoIdioma(idioma);
		request.setIdEntidad(entidad);
		JAXBElement<ConsultaCatalogoCategoriasResponse> response = (JAXBElement<ConsultaCatalogoCategoriasResponse>) getWebServiceTemplate()
				.marshalSendAndReceive("http://demo.gexflow.com:8080/gexflowzk/ws/CatalogoTramites",
						this.factory.createConsultaCatalogoCategorias(request), null);

		return response.getValue();
	}

}
