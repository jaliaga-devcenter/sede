package teralco.sedeelectronica.auth.client;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import admin.wsdl.ConsultaPermisoUsuario;
import admin.wsdl.ConsultaPermisoUsuarioResponse;
import admin.wsdl.ObjectFactory;

public class AuthClient extends WebServiceGatewaySupport {

	private ObjectFactory factory = new ObjectFactory();
	private String endpoint;

	public AuthClient(String pEndpoint) {
		super();
		this.endpoint = pEndpoint;

	}

	public Boolean tienePermiso(Integer entidad, String usuario, String contrasenya, String permiso) {
		ConsultaPermisoUsuario request = new ConsultaPermisoUsuario();
		request.setIdEntidad(entidad);
		request.setUsuario(usuario);
		request.setContrasenya(contrasenya);
		request.setPermiso(permiso);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaPermisoUsuarioResponse> response = (JAXBElement<ConsultaPermisoUsuarioResponse>) invokeWS(
				this.factory.createConsultaPermisoUsuario(request));

		return response.getValue().isSalida();

	}

	private JAXBElement<?> invokeWS(JAXBElement<?> requestWS) {
		return (JAXBElement<?>) getWebServiceTemplate().marshalSendAndReceive(this.endpoint, requestWS, null);

	}

}
