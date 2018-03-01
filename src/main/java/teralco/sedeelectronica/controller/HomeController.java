package teralco.sedeelectronica.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import teralco.sedeelectronica.verifirma.VerifirmaClient;

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
	private VerifirmaClient verifirmaClient;

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

	/*************/
	/* SERVICIOS */
	/*************/
	@RequestMapping(value = "/servicios/{id_cat}", method = RequestMethod.GET)
	public String getFile(@PathVariable("id_cat") Integer idCat, Model model) throws NumberFormatException, Exception {

		List<CategoriaDTO> categorias = this.clienteWS.getCategorias(ENTIDAD, this.IDIOMA);
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		// List<ServicioDTO> servicios = this.clienteWS.getServicios(ENTIDAD,
		// this.IDIOMA, idCat);
		CategoriaDTO cat = categorias.get(categorias.indexOf(new CategoriaDTO(idCat, "", "")));
		List<SubcategoriaDTO> subcategorias = cat.getSubcategorias();

		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);
		model.addAttribute("currentCat", cat);
		model.addAttribute("subcategorias", subcategorias);
		return "servicios/areas";
	}

	/*****************/
	/* FIN SERVICIOS */
	/*****************/

	/*************/
	/* VERIFIRMA */
	/*************/
	@RequestMapping("/verifirma")
	public String verifirma(Model model) {
		model.addAttribute("CSVValidation", new CSVValidation());

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
		File fileDownload;
		try {
			/* envio de solicitud.. */
			fileDownload = this.verifirmaClient.obtenerDocumentoPorCvd(ENTIDAD, CSV.csv);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fileDownload = null;
			model.addAttribute("message", "Ha ocurrido un error en el servicio.");
			return "verifirma/verifirma";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "Ha ocurrido un error con el fichero.");
			fileDownload = null;
			return "verifirma/verifirma";
		}

		return "/verifirma/download/" + fileDownload;
	}

	@RequestMapping(value = "/verifirma/download/{file_name}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getFile(@PathVariable("file_name") File file, HttpServletResponse response) {
		try {

			// get your file as InputStream
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			response.setContentType("application/pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*****************/
	/* FIN VERIFIRMA */
	/*****************/

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