package teralco.sedeelectronica.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

@Controller
public class HomeController {

	private static final String CAT_MODEL = "categorias";
	private static final String ICONO_MODEL = "iconos";

	private String idioma = "es";
	private static final Integer ENTIDAD = 0;
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
			categorias = this.clienteWS.getCategorias(ENTIDAD, this.idioma);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);

		return "index";
	}

	@RequestMapping(value = "/servicios/{id_cat}", method = RequestMethod.GET)
	public String getServiciosPorCategoria(@PathVariable("id_cat") Integer idCat, Model model) {

//		TODO Que pasa si la categoria no existe?
		List<CategoriaDTO> categorias = null;
		try {
			categorias = this.clienteWS.getCategorias(ENTIDAD, this.idioma);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		Optional<CategoriaDTO> categoria = categorias.stream().filter(cat -> cat.getIdCategoria().equals(idCat))
				.findFirst();

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);
		model.addAttribute("currentCat", categoria.isPresent() ? categoria.get() : null);
		
		return "servicios/areas";
	}

	@RequestMapping("/tramites")
	public String tramites(Model model) {
		List<CategoriaDTO> categorias;
		try {
			categorias = this.clienteWS.getCategorias(ENTIDAD, this.idioma);
		} catch (GexflowWSException e) {
			throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
		}
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);
		return "tramites";
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "perfil-del-contratante";
	}


	private Map<Integer, IconoDTO> getIconosPorCategoria(List<CategoriaDTO> categorias) {
		Map<Integer, IconoDTO> iconos = categorias.stream().map(categoria -> {
			try {
				return this.clienteWS.getIconoCategoria(ENTIDAD, this.idioma, categoria.getIdCategoria());
			} catch (GexflowWSException e) {
				throw new SedeElectronicaException(ExceptionType.THIRD_PARTY_SERVICE_ERROR, e);
			}
		}).collect(Collectors.toMap(IconoDTO::getIdCategoria, icono -> icono));
		return iconos;
	}

}