package teralco.sedeelectronica.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

@Controller
public class HomeController {

	private String IDIOMA = "es";
	private static final Integer ENTIDAD = 0;
	protected Locale locale;

	@Autowired
	private GexflowClient clienteWS;

	@Autowired
	public HomeController() {
		this.locale = LocaleContextHolder.getLocale();
		this.IDIOMA = this.locale.getLanguage();
	}

	@RequestMapping("/")
	public String greeting(Model model) throws GexflowWSException {
		List<CategoriaDTO> categorias = this.clienteWS.getCategorias(ENTIDAD, this.IDIOMA);
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);
		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);
		return "index";
	}

	@RequestMapping(value = "/servicios/{id_cat}", method = RequestMethod.GET)
	public String getFile(@PathVariable("id_cat") Integer idCat, Model model) throws NumberFormatException, Exception {

		List<CategoriaDTO> categorias = this.clienteWS.getCategorias(ENTIDAD, this.IDIOMA);
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		// List<ServicioDTO> servicios = this.clienteWS.getServicios(ENTIDAD,
		// this.IDIOMA, idCat);
		CategoriaDTO cat = categorias.get(categorias.indexOf(new CategoriaDTO(idCat, "", "")));
		model.addAttribute("currentCat", cat);
		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);

		return "servicios/areas";
	}

	@RequestMapping("/tramites")
	public String tramites(Model model) throws GexflowWSException {
		List<CategoriaDTO> categorias = this.clienteWS.getCategorias(ENTIDAD, this.IDIOMA);
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);
		return "tramites";
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "perfil-del-contratante";
	}

	private Map<Integer, IconoDTO> getIconosPorCategoria(List<CategoriaDTO> categorias) {
		Map<Integer, IconoDTO> iconos = categorias.stream().map(categoria -> {
			try {
				return this.clienteWS.getIconoCategoria(ENTIDAD, this.IDIOMA, categoria.getIdCategoria());
			} catch (GexflowWSException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toMap(IconoDTO::getIdCategoria, icono -> icono));
		return iconos;
	}

}