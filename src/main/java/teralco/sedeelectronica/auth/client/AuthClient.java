package teralco.sedeelectronica.auth.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import admin.wsdl.ConsultaDiasFestivos;
import admin.wsdl.ConsultaDiasFestivosResponse;
import admin.wsdl.ConsultaPermisoUsuario;
import admin.wsdl.ConsultaPermisoUsuarioResponse;
import admin.wsdl.ObjectFactory;
import teralco.sedeelectronica.auth.dto.DiaFestivoDTO;

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

	public List<DiaFestivoDTO> getCalendario(Integer entidad, String idioma, Integer anyo) {
		ConsultaDiasFestivos request = new ConsultaDiasFestivos();
		request.setIdEntidad(entidad);
		request.setCodigoIdioma(idioma);
		request.setAnyo(anyo);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaDiasFestivosResponse> response = (JAXBElement<ConsultaDiasFestivosResponse>) invokeWS(
				this.factory.createConsultaDiasFestivos(request));

		ArrayList<DiaFestivoDTO> calendario = new ArrayList<>();
		response.getValue().getSalida().stream().forEach(diaFestivo -> calendario.add(new DiaFestivoDTO("", "", "")));
		DiaFestivoDTO d = new DiaFestivoDTO("12", "Santa Eugenia", "FESTIVO");
		calendario.add(d);
		return calendario;
	}

	private JAXBElement<?> invokeWS(JAXBElement<?> requestWS) {
		return (JAXBElement<?>) getWebServiceTemplate().marshalSendAndReceive(this.endpoint, requestWS, null);

	}

}
