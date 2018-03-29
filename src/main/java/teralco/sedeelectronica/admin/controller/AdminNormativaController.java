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

import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.model.Normativa;
import teralco.sedeelectronica.model.NormativaLenguaje;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.service.NormativaService;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminNormativaController {

	private static String list = "admin/normativa/normativas";
	private static String redirList = "redirect:/admin/normativa";
	private static String form = "admin/normativa/formNormativa";
	private static String langModel = "langs";

	private NormativaService normativaService;
	private LenguajeService lenguajeService;

	@Autowired
	public AdminNormativaController(NormativaService pNormativaService, LenguajeService pLenguajeService) {
		this.normativaService = pNormativaService;
		this.lenguajeService = pLenguajeService;
	}

	@RequestMapping(value = "/admin/normativa", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Normativa> pages = this.normativaService.listAllByPage(pageable);
		model.addAttribute("normativas", pages);
		PageWrapper<Normativa> page = new PageWrapper<>(pages, "/normativa");
		model.addAttribute("page", page);
		return list;
	}

	@RequestMapping("/admin/normativa/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Normativa norma = new Normativa();
		lang.forEach(e -> norma.getTraducciones().add(new NormativaLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);
		model.addAttribute("normativa", norma);
		return form;
	}

	@RequestMapping("/admin/normativa/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Normativa norma = this.normativaService.get(id);

		List<String> source = new ArrayList<>();
		norma.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				norma.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				norma.getTraducciones().add(atomicInteger.get(), new NormativaLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("normativa", norma);
		return form;
	}

	@RequestMapping("/admin/normativa/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.normativaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La normativa " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/normativa/edit/", "/admin/normativa/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/normativa/save")
	public String save(@Valid @ModelAttribute("normativa") Normativa normativa, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		this.normativaService.save(normativa);
		return redirList;
	}
}
