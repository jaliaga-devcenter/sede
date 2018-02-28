package teralco.sedeelectronica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teralco.sedeelectronica.captcha.service.RecaptchaService;
import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;
import teralco.sedeelectronica.model.CSVValidation;

@Controller
public class HomeController {

	private String IDIOMA = "es";
	private static final Integer ENTIDAD = 0;
	protected Locale locale;

	@Autowired
	private GexflowClient clienteWS;
	@Autowired
	private RecaptchaService captchaService;

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
		List<SubcategoriaDTO> subcategorias = cat.getSubcategorias();
		List<String> nombre = new ArrayList<>();
		for (SubcategoriaDTO sub : subcategorias) {
			nombre.add(sub.getNombre());
		}

		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);
		model.addAttribute("currentCat", cat);
		model.addAttribute("subcategorias", subcategorias);
		model.addAttribute("nombre", nombre);
		return "servicios/areas";
	}

	@RequestMapping("/verifirma")
	public String verifirma() {

		return "verifirma/verifirma";
	}

	/**
	 * @param CSV
	 */
	@PostMapping("/verifirma/send")
	public String signup(@Valid @ModelAttribute("CSVValidation") CSVValidation CSV, BindingResult bindingResult,
			Model model, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "verifirma/verifirma";
		}

		String ip = request.getRemoteAddr();

		String captchaVerifyMessage = this.captchaService.verifyRecaptcha(ip, recaptchaResponse);

		if (!captchaVerifyMessage.isEmpty()) {
			model.addAttribute("messageWarning", captchaVerifyMessage);
			return "verifirma/verifirma";
		}

		try {
			/* envio de solicitud.. */

		} catch (@SuppressWarnings("unused") MailException e) {
			model.addAttribute("message", "Ha ocurrido un error enviando el correo");
			return "verifirma/verifirma";
		}

		return "/verifirma/verifirma";
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