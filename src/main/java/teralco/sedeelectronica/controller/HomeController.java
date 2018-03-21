package teralco.sedeelectronica.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.ServicioDTO;
import teralco.sedeelectronica.security.CertAuthenticationToken;
import teralco.sedeelectronica.security.UsuarioSede;
import teralco.sedeelectronica.service.CategoriaService;
import teralco.sedeelectronica.utils.LanguageUtils;

@Controller
public class HomeController {

	private static final String CAT_MODEL = "categorias";
	private static final String ICONO_MODEL = "iconos";
	private static final String SERVICIOS_MODEL = "servicios";
	private static final String SERVICIO_MODEL = "servicio";

	@Value("${sede.entidad}")
	private Integer entidad;

	@Value("${sede.iniciar.tramite}")
	private String iniciarTramiteUrlPattern;

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	@RequestMapping("/")
	public String greeting(Model model) {

		List<CategoriaDTO> categorias = this.categoriaService.getCategorias(this.entidad, LanguageUtils.getLanguage());
		Map<Integer, IconoDTO> iconos = this.categoriaService.getIconosPorCategoria(this.entidad,
				LanguageUtils.getLanguage(), categorias);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(ICONO_MODEL, iconos);

		return "index";
	}

	@RequestMapping(value = { "/categorias", "/categorias/{id_cat}" }, method = RequestMethod.GET)
	public String getServiciosPorCategoria(@PathVariable("id_cat") Optional<Integer> idCat, Model model) {

		List<CategoriaDTO> categorias = this.categoriaService.getCategorias(this.entidad, LanguageUtils.getLanguage());
		Map<Integer, IconoDTO> iconos = this.categoriaService.getIconosPorCategoria(this.entidad,
				LanguageUtils.getLanguage(), categorias);

		CategoriaDTO categoriaActual = getCategoriaActual(categorias, idCat);
		Map<Integer, List<ServicioDTO>> servicios = this.categoriaService.getServiciosPorSubCategorias(this.entidad,
				LanguageUtils.getLanguage(), categoriaActual);

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

	@RequestMapping(value = "/buscador-procedimientos")
	public String getTodosServicios(Model model) {

		List<CategoriaDTO> categorias = this.categoriaService.getCategorias(this.entidad, LanguageUtils.getLanguage());
		Map<Integer, List<ServicioDTO>> servicios = new HashMap<>();

		categorias.forEach(cat -> servicios.putAll(
				this.categoriaService.getServiciosPorSubCategorias(this.entidad, LanguageUtils.getLanguage(), cat)));

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(SERVICIOS_MODEL, servicios);

		return "servicios/procedimientos";
	}

	@RequestMapping(value = "/buscar-procedimientos", method = { RequestMethod.POST, RequestMethod.GET })
	public String getBusquedaServicios(@RequestParam(value = "searchText", required = false) String searchText,
			Model model) {

		List<CategoriaDTO> categorias = this.categoriaService.getCategorias(this.entidad, LanguageUtils.getLanguage());
		Map<Integer, List<ServicioDTO>> servicios = new HashMap<>();

		Predicate<ServicioDTO> filtro = searchText == null ? null
				: servicio -> servicio.getDescripcion() != null && servicio.getDescripcion().contains(searchText);

		categorias.forEach(cat -> servicios.putAll(this.categoriaService.getServiciosPorSubCategorias(this.entidad,
				LanguageUtils.getLanguage(), cat, Optional.ofNullable(filtro))));

		Predicate<CategoriaDTO> noContieneServicios = cat -> cat.getSubcategorias().stream()
				.filter(sub -> !(servicios.get(sub.getIdSubcategoria()).isEmpty())).count() == 0;

		categorias.removeIf(noContieneServicios);

		model.addAttribute(CAT_MODEL, categorias);
		model.addAttribute(SERVICIOS_MODEL, servicios);
		model.addAttribute("searchText", searchText);

		return "servicios/procedimientos";
	}

	@RequestMapping("/ficha-procedimiento/{id}")
	public String fichaProcedimiento(@PathVariable(value = "id") Integer idServicio, Model model) {
		ServicioDTO servicio = this.categoriaService.getServicio(this.entidad, LanguageUtils.getLanguage(), idServicio);
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

}