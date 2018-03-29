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

import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.model.AvisoLenguaje;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.AvisoService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminAvisoController {

	private static String list = "admin/avisos/avisos";
	private static String redirList = "redirect:/admin/avisos";
	private static String form = "admin/avisos/formAviso";
	private static String langModel = "langs";

	private AvisoService avisoService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminAvisoController(AvisoService pAvisoService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.avisoService = pAvisoService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/avisos", produces = "text/html;charset=UTF-8")
	public String avisos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Aviso> pages = this.avisoService.listAllByPage(pageable);
		model.addAttribute("avisos", pages);
		PageWrapper<Aviso> page = new PageWrapper<>(pages, "/avisos");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/admin/avisos/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Aviso aviso = new Aviso();
		lang.forEach(e -> aviso.getTraducciones().add(new AvisoLenguaje(e.getCodigo())));

		model.addAttribute(langModel, lang);
		model.addAttribute("aviso", aviso);
		return form;
	}

	@RequestMapping("/admin/avisos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Aviso aviso = this.avisoService.get(id);

		List<String> source = new ArrayList<>();
		aviso.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				aviso.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				aviso.getTraducciones().add(atomicInteger.get(), new AvisoLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("aviso", this.avisoService.get(id));
		return form;
	}

	@RequestMapping("/admin/avisos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {

		this.avisoService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El aviso " + id + " ha sido borrado.");
		return redirList;
	}

	@RequestMapping({ "/admin/avisos/edit/", "/admin/avisos/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/avisos/save")

	public String save(@Valid @ModelAttribute("aviso") Aviso aviso, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(aviso.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			aviso.setFichero(file);
		}
		this.avisoService.save(aviso);
		return redirList;
	}

}
