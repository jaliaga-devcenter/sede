package teralco.sedeelectronica.gexflow.client;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import gexflow.wsdl.ConsultaCatalogoCategorias;
import gexflow.wsdl.ConsultaCatalogoCategoriasResponse;
import gexflow.wsdl.ConsultaCatalogoServicioPorCategoria;
import gexflow.wsdl.ConsultaCatalogoServicioPorCategoriaResponse;
import gexflow.wsdl.ConsultaCatalogoServicioPorId;
import gexflow.wsdl.ConsultaCatalogoServicioPorIdResponse;
import gexflow.wsdl.ConsultaIconoCategoria;
import gexflow.wsdl.ConsultaIconoCategoriaResponse;
import gexflow.wsdl.EntradaConsultaCategoria;
import gexflow.wsdl.EstadoRespuesta;
import gexflow.wsdl.ObjectFactory;
import gexflow.wsdl.RespuestaCategoriaWS;
import gexflow.wsdl.SubcategoriaFiltroWS;
import gexflow.wsdl.SubcategoriasFiltroWS;
import teralco.sedeelectronica.gexflow.converter.CategoriaConverter;
import teralco.sedeelectronica.gexflow.converter.IconoConverter;
import teralco.sedeelectronica.gexflow.converter.ServicioConverter;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;
import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

public class GexflowClient extends WebServiceGatewaySupport {

	private ObjectFactory factory = new ObjectFactory();
	private String endpoint;

	@Autowired
	private CategoriaConverter categoriaConverter;

	@Autowired
	private IconoConverter iconoConverter;

	@Autowired
	private ServicioConverter servicioConverter;

	public GexflowClient(String pEndpoint) {
		super();
		this.endpoint = pEndpoint;

	}

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

	public List<ServicioDTO> getServicios(Integer entidad, String idioma, CategoriaDTO categoria,
			SubcategoriaDTO subcategoria) throws GexflowWSException {

		ConsultaCatalogoServicioPorCategoria request = new ConsultaCatalogoServicioPorCategoria();
		request.setIdEntidad(entidad);
		request.setCodigoIdioma(idioma);
		request.setEntradaConsultaCategoria(new EntradaConsultaCategoria());
		request.getEntradaConsultaCategoria().setIdCategoria(categoria.getIdCategoria());
		request.getEntradaConsultaCategoria().setSubcategorias(new SubcategoriasFiltroWS());

		SubcategoriaFiltroWS subcategoriaFiltroWS = new SubcategoriaFiltroWS();
		subcategoriaFiltroWS.setIdSubcategoria(subcategoria.getIdSubcategoria());

		request.getEntradaConsultaCategoria().getSubcategorias().getSubcategoria().add(subcategoriaFiltroWS);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaCatalogoServicioPorCategoriaResponse> response = (JAXBElement<ConsultaCatalogoServicioPorCategoriaResponse>) invokeWS(
				this.factory.createConsultaCatalogoServicioPorCategoria(request));

		EstadoRespuesta estado = response.getValue().getResultado().getEstadoRespuesta();
		comprobarError(estado);

		List<ServicioDTO> servicios = this.servicioConverter
				.createFromEntities(response.getValue().getResultado().getServicios().getServicio());

		servicios.stream().forEach(servicio -> servicio.setIdCategoria(categoria.getIdCategoria())
				.setIdSubCategoria(subcategoria.getIdSubcategoria()));

		return servicios;

	}

	public ServicioDTO getServicio(Integer entidad, String idioma, Integer idServicio) throws GexflowWSException {

		ConsultaCatalogoServicioPorId request = new ConsultaCatalogoServicioPorId();
		request.setIdEntidad(entidad);
		request.setCodigoIdioma(idioma);
		request.setIdentificadorServicio(idServicio);

		@SuppressWarnings("unchecked")
		JAXBElement<ConsultaCatalogoServicioPorIdResponse> response = (JAXBElement<ConsultaCatalogoServicioPorIdResponse>) invokeWS(
				this.factory.createConsultaCatalogoServicioPorId(request));

		EstadoRespuesta estado = response.getValue().getResultado().getEstadoRespuesta();
		comprobarError(estado);

		return this.servicioConverter
				.createFrom(response.getValue().getResultado().getServicios().getServicio().get(0));
	}

	private JAXBElement<?> invokeWS(JAXBElement<?> requestWS) {
		return (JAXBElement<?>) getWebServiceTemplate().marshalSendAndReceive(this.endpoint, requestWS, null);

	}

	private static void comprobarError(EstadoRespuesta estadoRespuesta) throws GexflowWSException {
		if (estadoRespuesta.isError()) {
			throw new GexflowWSException(estadoRespuesta.getMensaje());
		}

	}

}
