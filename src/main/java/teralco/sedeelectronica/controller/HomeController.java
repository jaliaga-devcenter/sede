package teralco.sedeelectronica.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;
import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;
import teralco.sedeelectronica.security.CertAuthenticationToken;
import teralco.sedeelectronica.security.UsuarioSede;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private static final String CAT_MODEL = "categorias";
	private static final String ICONO_MODEL = "iconos";
	private static final String SERVICIOS_MODEL = "servicios";
	private static final String SERVICIO_MODEL = "servicio";

	private String idioma = "es";

	@Value("${sede.entidad}")
	private Integer ENTIDAD;

	@Value("${sede.iniciar.tramite}")
	private String iniciarTramiteUrlPattern;

	protected Locale locale;

	@Autowired
	private GexflowClient clienteWS;

	@Autowired
	public HomeController() {
		this.locale = LocaleContextHolder.getLocale();
		this.idioma = this.locale.getLanguage();
	}

	@RequestMapping("/")
	public String greeting(Model model) {
		List<CategoriaDTO> categorias;

		try {
			categorias = this.clienteWS.getCategorias(this.ENTIDAD, this.idioma);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);

		return "index";
	}

	@RequestMapping(value = { "/servicios", "/servicios/{id_cat}" }, method = RequestMethod.GET)
	public String getServiciosPorCategoria(@PathVariable("id_cat") Optional<Integer> idCat, Model model) {

		List<CategoriaDTO> categorias = null;
		try {
			categorias = this.clienteWS.getCategorias(this.ENTIDAD, this.idioma);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		CategoriaDTO categoriaActual = getCategoriaActual(categorias, idCat);
		Map<Integer, List<ServicioDTO>> servicios = getServiciosPorSubCategorias(categoriaActual);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);

		model.addAttribute(SERVICIOS_MODEL, servicios);
		model.addAttribute("currentCat", categoriaActual);

		return "servicios/areas";
	}

	private static CategoriaDTO getCategoriaActual(List<CategoriaDTO> categorias, Optional<Integer> idCat) {
		if (!idCat.isPresent()) {
			return categorias.get(0);
		}
		Optional<CategoriaDTO> categoriaActual = categorias.stream()
				.filter(cat -> cat.getIdCategoria().equals(idCat.get())).findFirst();
		return categoriaActual.isPresent() ? categoriaActual.get() : categorias.get(0);
	}

	@RequestMapping("/ficha-procedimiento/{id}")
	public String fichaProcedimiento(@PathVariable(value = "id") Integer idServicio, Model model) {
		ServicioDTO servicio = null;
		try {
			servicio = this.clienteWS.getServicio(this.ENTIDAD, this.idioma, idServicio);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}

		model.addAttribute(SERVICIO_MODEL, servicio);
		return "servicios/ficha-procedimiento";
	}

	@RequestMapping("/procedimiento/{id}")
	public String procedimientos(@PathVariable("id") Integer idServicio, Model model, Principal authentication) {
		UsuarioSede usuario = (UsuarioSede) ((CertAuthenticationToken) authentication).getPrincipal();

		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("idServicio", idServicio);
		uriVariables.put("ticket", usuario.getTicket());
		String url = UriComponentsBuilder.fromHttpUrl(this.iniciarTramiteUrlPattern).buildAndExpand(uriVariables)
				.toString();
		model.addAttribute("iframeUrl", url);
		return "servicios/procedimiento";
	}

	@RequestMapping("/buscador-procedimientos")
	public String buscadorProcedimientos() {
		return "servicios/buscador-procedimientos";
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "perfil-del-contratante";
	}

	private Map<Integer, IconoDTO> getIconosPorCategoria(List<CategoriaDTO> categorias) {
		Map<Integer, IconoDTO> iconos = categorias.stream().map(categoria -> {
			try {
				return this.clienteWS.getIconoCategoria(this.ENTIDAD, this.idioma, categoria.getIdCategoria());
			} catch (GexflowWSException e) {
				throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
			}
		}).collect(Collectors.toMap(IconoDTO::getIdCategoria, icono -> icono));
		return iconos;
	}

	private Map<Integer, List<ServicioDTO>> getServiciosPorSubCategorias(CategoriaDTO categoria) {
		Map<Integer, List<ServicioDTO>> returnList = new HashMap<>();

		for (SubcategoriaDTO subcategoria : categoria.getSubcategorias()) {
			List<ServicioDTO> servicios = null;
			try {
				servicios = this.clienteWS.getServicios(this.ENTIDAD, this.idioma, categoria, subcategoria);
			} catch (GexflowWSException e) {
				LOGGER.error(
						"Error en la invocación al servicio, probablemente no hayan servicios para esa subcategoria.",
						e);

			}
			returnList.put(subcategoria.getIdSubcategoria(), servicios);
		}

		return returnList;
	}

}