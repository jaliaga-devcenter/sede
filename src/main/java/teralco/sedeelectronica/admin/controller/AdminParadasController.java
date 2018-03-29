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
import teralco.sedeelectronica.model.Parada;
import teralco.sedeelectronica.model.ParadaLenguaje;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.service.ParadaService;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminParadasController {
	private static String list = "admin/paradas/paradas";
	private static String redirList = "redirect:/admin/paradas";
	private static String form = "admin/paradas/formParadas";
	private static String langModel = "langs";

	private ParadaService paradaService;
	private LenguajeService lenguajeService;

	@Autowired
	public AdminParadasController(ParadaService pParadaService, LenguajeService pLenguajeService) {
		this.paradaService = pParadaService;
		this.lenguajeService = pLenguajeService;
	}

	@RequestMapping(value = "/admin/paradas", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Parada> pages = this.paradaService.listAllByPage(pageable);
		model.addAttribute("paradas", pages);
		PageWrapper<Parada> page = new PageWrapper<>(pages, "/paradas");
		model.addAttribute("page", page);
		model.addAttribute("", this.paradaService.list());
		return list;
	}

	@RequestMapping("/admin/paradas/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Parada parada = new Parada();
		lang.forEach(e -> parada.getTraducciones().add(new ParadaLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);
		model.addAttribute("parada", parada);
		return form;
	}

	@RequestMapping("/admin/paradas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Parada parada = this.paradaService.get(id);

		List<String> source = new ArrayList<>();
		parada.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				parada.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();
		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				parada.getTraducciones().add(atomicInteger.get(), new ParadaLenguaje(s));
			}
			atomicInteger.getAndIncrement();

		});
		model.addAttribute(langModel, langs);
		model.addAttribute("parada", parada);
		return form;
	}

	@RequestMapping("/admin/paradas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.paradaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La parada " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/paradas/edit/", "/admin/paradas/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/paradas/save")
	public String save(@Valid @ModelAttribute("parada") Parada parada, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
			return form;
		}

		this.paradaService.save(parada);
		return redirList;
	}

}
