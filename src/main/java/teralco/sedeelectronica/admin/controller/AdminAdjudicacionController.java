package teralco.sedeelectronica.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.AdjudicacionLenguaje;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.AdjudicacionService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminAdjudicacionController {

	private static String list = "admin/adjudicaciones/adjudicaciones";
	private static String redirList = "redirect:/admin/adjudicaciones";
	private static String form = "admin/adjudicaciones/formAdjudicacion";
	private static String langModel = "langs";

	private AdjudicacionService adjudicacionService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminAdjudicacionController(AdjudicacionService pAdjudicacionService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.adjudicacionService = pAdjudicacionService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/adjudicaciones", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Adjudicacion> pages = this.adjudicacionService.listAllByPage(pageable);
		model.addAttribute("adjudicaciones", pages);
		PageWrapper<Adjudicacion> page = new PageWrapper<>(pages, "/adjudicaciones");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/admin/adjudicaciones/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Adjudicacion adju = new Adjudicacion();
		lang.forEach(e -> adju.getTraducciones().add(new AdjudicacionLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);

		model.addAttribute("adjudicacion", adju);
		return form;
	}

	@RequestMapping("/admin/adjudicaciones/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Adjudicacion adju = this.adjudicacionService.get(id);

		List<String> source = new ArrayList<>();
		adju.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				adju.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				adju.getTraducciones().add(atomicInteger.get(), new AdjudicacionLenguaje(s));
			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("adjudicacion", adju);
		return form;
	}

	@RequestMapping("/admin/adjudicaciones/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.adjudicacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La adjudicación " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/adjudicaciones/edit/", "/admin/adjudicaciones/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/adjudicaciones/save")
	public String save(@Valid @ModelAttribute("adjudicacion") Adjudicacion adjudicacion, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(adjudicacion.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			adjudicacion.setResultado(file);
		}

		this.adjudicacionService.save(adjudicacion);
		return redirList;
	}
}
