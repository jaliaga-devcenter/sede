package teralco.sedeelectronica.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.service.AdjudicacionService;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.AvisoService;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.service.LicitacionService;
import teralco.sedeelectronica.service.ModeloService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.LanguageUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class PerfilContratanteController {

	private static String encrypt = "encrypt";

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	private LicitacionService licitacionService;

	@Autowired
	private AperturaService aperturaService;

	@Autowired
	private AdjudicacionService adjudicacionService;

	@Autowired
	private DocumentacionService documentacionService;

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private AvisoService avisoService;

	private List<String> target = new ArrayList<>();

	@Autowired
	public PerfilContratanteController(LenguajeService lenguajeService) {
		Iterable<Lenguaje> langs = lenguajeService.list();
		langs.forEach(e -> this.target.add(e.getCodigo()));
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated()) {
			return "redirect:/admin";
		}

		return "redirect:/licitaciones";

	}

	@RequestMapping("/licitaciones")
	public String licitaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {

		Page<Licitacion> pages = this.licitacionService.listAllByPage(pageable);

		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));

		model.addAttribute("licitaciones", pages);
		PageWrapper<Licitacion> page = new PageWrapper<>(pages, "/licitaciones");
		model.addAttribute("page", page);

		model.addAttribute(encrypt, this.encryptUtils);
		return "contratante/licitaciones";
	}

	@RequestMapping("/aperturas")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Apertura> pages = this.aperturaService.listAllByPage(pageable);
		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));
		model.addAttribute("aperturas", pages);
		PageWrapper<Apertura> page = new PageWrapper<>(pages, "/aperturas");

		model.addAttribute("page", page);
		model.addAttribute(encrypt, this.encryptUtils);

		return "contratante/aperturas";
	}

	@RequestMapping("/adjudicaciones")
	public String adjudicaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Adjudicacion> pages = this.adjudicacionService.listAllByPage(pageable);
		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));
		model.addAttribute("adjudicaciones", pages);

		PageWrapper<Adjudicacion> page = new PageWrapper<>(pages, "/adjudicaciones");

		model.addAttribute("page", page);
		model.addAttribute(encrypt, this.encryptUtils);

		return "contratante/adjudicaciones";
	}

	@RequestMapping("/documentos")
	public String documentos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Documentacion> pages = this.documentacionService.listAllByPage(pageable);
		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));
		model.addAttribute("documentos", pages);

		PageWrapper<Documentacion> page = new PageWrapper<>(pages, "/documentos");

		model.addAttribute("page", page);
		model.addAttribute(encrypt, this.encryptUtils);

		return "contratante/documentos";
	}

	@RequestMapping("/modelos")
	public String modelos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Modelo> pages = this.modeloService.listAllByPage(pageable);
		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));
		model.addAttribute("modelos", pages);

		PageWrapper<Modelo> page = new PageWrapper<>(pages, "/modelos");

		model.addAttribute("page", page);
		model.addAttribute(encrypt, this.encryptUtils);

		return "contratante/modelos";
	}

	@RequestMapping("/avisos")
	public String avisos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Aviso> pages = this.avisoService.listAllByPage(pageable);
		final String[] lang = { LocaleContextHolder.getLocale().toString() };
		if (!this.target.contains(lang[0])) {
			lang[0] = LanguageUtils.SPANISH;
		}

		pages.forEach(s -> s.getTraducciones().removeIf(i -> !i.getIdioma().equals(lang[0])));
		model.addAttribute("avisos", pages);

		PageWrapper<Aviso> page = new PageWrapper<>(pages, "/avisos");

		model.addAttribute("page", page);
		model.addAttribute(encrypt, this.encryptUtils);
		return "contratante/avisos";
	}

}
