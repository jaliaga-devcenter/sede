package teralco.sedeelectronica.pasarela.client;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import pasarela.wsdl.DesencriptarDatos;
import pasarela.wsdl.DesencriptarDatosResponse;
import pasarela.wsdl.ObjectFactory;

public class PasarelaClient extends WebServiceGatewaySupport {

	private ObjectFactory factory = new ObjectFactory();
	private String endpoint;

	public PasarelaClient(String pEndpoint) {
		super();
		this.endpoint = pEndpoint;

	}

	public String desencriptar(String datosCifrados) {
		DesencriptarDatos request = new DesencriptarDatos();
		request.setDatosCifrados(datosCifrados);

		@SuppressWarnings("unchecked")
		JAXBElement<DesencriptarDatosResponse> response = (JAXBElement<DesencriptarDatosResponse>) invokeWS(
				this.factory.createDesencriptarDatos(request));

		return response.getValue().getSalida();

	}

	private JAXBElement<?> invokeWS(JAXBElement<?> requestWS) {
		return (JAXBElement<?>) getWebServiceTemplate().marshalSendAndReceive(this.endpoint, requestWS, null);

	}

}
