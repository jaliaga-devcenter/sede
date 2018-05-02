package teralco.sedeelectronica.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.model.LicitacionLenguaje;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.service.LicitacionService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminLicitacionController {

	private static String list = "admin/licitaciones/licitaciones";
	private static String redirList = "redirect:/admin/licitaciones";
	private static String form = "admin/licitaciones/formLicitacion";

	private static String langModel = "langs";

	private LicitacionService licitacionService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	public AdminLicitacionController(LicitacionService pLicitacionService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.licitacionService = pLicitacionService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/licitaciones", produces = "text/html;charset=UTF-8")
	public String licitaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Licitacion> pages = this.licitacionService.listAllByPage(pageable);
		model.addAttribute("licitaciones", pages);
		PageWrapper<Licitacion> page = new PageWrapper<>(pages, "/admin/licitaciones");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/admin/licitaciones/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Licitacion lici = new Licitacion();
		lang.forEach(e -> lici.getTraducciones().add(new LicitacionLenguaje(e.getCodigo())));
		model.addAttribute("licitacion", lici);
		model.addAttribute(langModel, lang);
		return form;
	}

	@RequestMapping("/admin/licitaciones/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Licitacion lici = this.licitacionService.get(id);

		List<String> source = new ArrayList<>();
		lici.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				lici.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				lici.getTraducciones().add(atomicInteger.get(), new LicitacionLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute("licitacion", lici);
		model.addAttribute(langModel, langs);
		return form;

	}

	@RequestMapping("/admin/licitaciones/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.licitacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La licitacion " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/licitaciones/edit/", "/admin/licitaciones/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/licitaciones/save")
	public String save(@Valid @ModelAttribute("licitacion") Licitacion lici, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(lici.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			lici.setFichero(file);
		}
		this.licitacionService.save(lici);
		return redirList;
	}

}
