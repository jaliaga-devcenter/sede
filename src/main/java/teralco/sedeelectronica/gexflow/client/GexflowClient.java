package teralco.sedeelectronica.gexflow.client;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import gexflow.wsdl.ConsultaCatalogoCategorias;
import gexflow.wsdl.ConsultaCatalogoCategoriasResponse;
import gexflow.wsdl.ConsultaCatalogoServicio;
import gexflow.wsdl.ConsultaCatalogoServicioPorCategoria;
import gexflow.wsdl.ConsultaCatalogoServicioPorCategoriaResponse;
import gexflow.wsdl.ConsultaCatalogoServicioResponse;
import gexflow.wsdl.ConsultaIconoCategoria;
import gexflow.wsdl.ConsultaIconoCategoriaResponse;
import gexflow.wsdl.EntradaConsultaCategoria;
import gexflow.wsdl.EntradaConsultaServicioCatalogo;
import gexflow.wsdl.EstadoRespuesta;
import gexflow.wsdl.ObjectFactory;
import gexflow.wsdl.RespuestaCategoriaWS;
import teralco.sedeelectronica.gexflow.converter.CategoriaConverter;
import teralco.sedeelectronica.gexflow.converter.IconoConverter;
import teralco.sedeelectronica.gexflow.converter.ServicioConverter;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

public class GexflowClient extends WebServiceGatewaySupport {

	private ObjectFactory factory = new ObjectFactory();

	@Autowired
	private CategoriaConverter categoriaConverter;

	@Autowired
	private IconoConverter iconoConverter;

	@Autowired
	private ServicioConverter servicioConverter;

	public List<CategoriaDTO> getCategorias(Integer entidad, String idioma) throws GexflowWSException {

		ConsultaCatalogoCategorias request = new ConsultaCatalogoCategorias();
		request.setCodigoIdioma(idioma);
		request.setIdEntidad(entidad);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaCatalogoCategoriasResponse> response = (JAXBElement<ConsultaCatalogoCategoriasResponse>) invokeWS(
				this.factory.createConsultaCatalogoCategorias(request));

		RespuestaCategoriaWS resultado = response.getValue().getResultado();
		comprobarError(resultado.getEstadoRespuesta());

		return this.categoriaConverter.createFromEntities(resultado.getCategorias().getCategoria());
	}

	public IconoDTO getIconoCategoria(Integer entidad, String idioma, Integer idCategoria) throws GexflowWSException {
		ConsultaIconoCategoria request = new ConsultaIconoCategoria();
		request.setCodigoIdioma(idioma);
		request.setIdEntidad(entidad);
		request.setIdCategoria(idCategoria);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaIconoCategoriaResponse> response = (JAXBElement<ConsultaIconoCategoriaResponse>) invokeWS(
				this.factory.createConsultaIconoCategoria(request));

		EstadoRespuesta estado = response.getValue().getResultado().getEstadoRespuesta();
		comprobarError(estado);

		return this.iconoConverter.createFrom(response.getValue().getResultado().getContenidoDocumento())
				.setIdCategoria(idCategoria);
	}

	public List<ServicioDTO> getServicios(Integer entidad, String idioma, Integer idCategoria)
			throws GexflowWSException {

		ConsultaCatalogoServicioPorCategoria request = new ConsultaCatalogoServicioPorCategoria();
		request.setIdEntidad(entidad);
		request.setCodigoIdioma(idioma);
		request.setEntradaConsultaCategoria(new EntradaConsultaCategoria());
		request.getEntradaConsultaCategoria().setIdCategoria(idCategoria);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaCatalogoServicioPorCategoriaResponse> response = (JAXBElement<ConsultaCatalogoServicioPorCategoriaResponse>) invokeWS(
				this.factory.createConsultaCatalogoServicioPorCategoria(request));

		EstadoRespuesta estado = response.getValue().getResultado().getEstadoRespuesta();
		comprobarError(estado);
		return this.servicioConverter
				.createFromEntities(response.getValue().getResultado().getServicios().getServicio());
	}

	public List<ServicioDTO> buscarServicios(Integer entidad, String idioma, String textoBusqueda)
			throws GexflowWSException {

		ConsultaCatalogoServicio request = new ConsultaCatalogoServicio();
		request.setIdEntidad(entidad);
		request.setCodigoIdioma(idioma);
		request.setEntradaConsultaServicioCatalogo(new EntradaConsultaServicioCatalogo());
		request.getEntradaConsultaServicioCatalogo().setDenominacionServicio(textoBusqueda);
		request.getEntradaConsultaServicioCatalogo().setDescripcion(textoBusqueda);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaCatalogoServicioResponse> response = (JAXBElement<ConsultaCatalogoServicioResponse>) invokeWS(
				this.factory.createConsultaCatalogoServicio(request));

		EstadoRespuesta estado = response.getValue().getResultado().getEstadoRespuesta();
		comprobarError(estado);
		return this.servicioConverter
				.createFromEntities(response.getValue().getResultado().getServicios().getServicio());
	}

	private JAXBElement<?> invokeWS(JAXBElement<?> requestWS) {
		return (JAXBElement<?>) getWebServiceTemplate()
				.marshalSendAndReceive("http://demo.gexflow.com:8080/gexflowzk/ws/CatalogoTramites", requestWS, null);

	}

	private static void comprobarError(EstadoRespuesta estadoRespuesta) throws GexflowWSException {
		if (estadoRespuesta.isError()) {
			throw new GexflowWSException(estadoRespuesta.getMensaje());
		}

	}

}
