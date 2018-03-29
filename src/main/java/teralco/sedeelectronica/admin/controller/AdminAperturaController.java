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

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.AperturaLenguaje;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminAperturaController {

	private static String list = "admin/aperturas/aperturas";
	private static String redirList = "redirect:/admin/aperturas";
	private static String form = "admin/aperturas/formApertura";
	private static String langModel = "langs";

	private AperturaService aperturaService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminAperturaController(AperturaService pAperturaService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.aperturaService = pAperturaService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/aperturas", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		model.addAttribute("encrypt", this.encryptUtils);

		Page<Apertura> pages = this.aperturaService.listAllByPage(pageable);
		model.addAttribute("aperturas", pages);
		PageWrapper<Apertura> page = new PageWrapper<>(pages, "/aperturas");
		model.addAttribute("page", page);

		return list;
	}

	@RequestMapping("/admin/aperturas/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Apertura aper = new Apertura();
		lang.forEach(e -> aper.getTraducciones().add(new AperturaLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);
		model.addAttribute("apertura", aper);
		return form;
	}

	@RequestMapping("/admin/aperturas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Apertura aper = this.aperturaService.get(id);

		List<String> source = new ArrayList<>();
		aper.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				aper.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				aper.getTraducciones().add(atomicInteger.get(), new AperturaLenguaje(s));
			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("apertura", aper);
		return form;
	}

	@RequestMapping("/admin/aperturas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.aperturaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La apertura " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/aperturas/edit/", "/admin/aperturas/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/aperturas/save")
	public String save(@Valid @ModelAttribute("apertura") Apertura apertura, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(apertura.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			apertura.setResultado(file);
		}
		this.aperturaService.save(apertura);
		return redirList;
	}
}
