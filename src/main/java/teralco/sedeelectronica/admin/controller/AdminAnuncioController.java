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

import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.model.AnuncioLenguaje;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.AnuncioService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminAnuncioController {

	private static String list = "admin/tablon-anuncios/anuncios";
	private static String redirList = "redirect:/admin/anuncios";
	private static String form = "admin/tablon-anuncios/formAnuncio";
	private static String langModel = "langs";

	private AnuncioService anuncioService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminAnuncioController(AnuncioService pAnuncioService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.anuncioService = pAnuncioService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/anuncios", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Anuncio> pages = this.anuncioService.listAllByPage(pageable);
		model.addAttribute("anuncios", pages);
		PageWrapper<Anuncio> page = new PageWrapper<>(pages, "/tablon-anuncios");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/admin/anuncios/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Anuncio anun = new Anuncio();
		lang.forEach(e -> anun.getTraducciones().add(new AnuncioLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);

		model.addAttribute("anuncio", anun);
		return form;
	}

	@RequestMapping("/admin/anuncios/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Anuncio anun = this.anuncioService.get(id);

		List<String> source = new ArrayList<>();
		anun.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				anun.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				anun.getTraducciones().add(atomicInteger.get(), new AnuncioLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("anuncio", this.anuncioService.get(id));
		return form;
	}

	@RequestMapping("/admin/anuncios/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.anuncioService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El anuncio " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/anuncios/edit/", "/admin/anuncios/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/anuncios/save")
	public String save(@Valid @ModelAttribute("anuncio") Anuncio anuncio, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(anuncio.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			anuncio.setFichero(file);
		}

		this.anuncioService.save(anuncio);
		return redirList;
	}

}
