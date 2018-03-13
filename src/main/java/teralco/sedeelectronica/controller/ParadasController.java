package teralco.sedeelectronica.controller;

import java.time.Instant;
import java.util.Date;

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

import teralco.sedeelectronica.model.Parada;
import teralco.sedeelectronica.service.ParadaService;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class ParadasController {
	private static String list = "paradas/paradas";
	private static String redirList = "redirect:/paradas";
	private static String form = "paradas/formParadas";

	private ParadaService paradaService;

	@Autowired
	public ParadasController(ParadaService pParadaService) {
		this.paradaService = pParadaService;
	}

	@RequestMapping(value = "/paradas", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Parada> pages = this.paradaService.listAllByPage(pageable);
		model.addAttribute("paradas", pages);
		PageWrapper<Parada> page = new PageWrapper<>(pages, "/paradas");
		model.addAttribute("page", page);
		model.addAttribute("", this.paradaService.list());
		return list;
	}

	@RequestMapping("/paradas/create")
	public String create(Model model) {
		model.addAttribute("parada", new Parada());
		return form;
	}

	@RequestMapping("/paradas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("parada", this.paradaService.get(id));
		return form;
	}

	@RequestMapping("/paradas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.paradaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La parada " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/paradas/save")
	public String save(@Valid @ModelAttribute("parada") Parada parada, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}

		this.paradaService.save(parada);
		return redirList;
	}

	@RequestMapping("/paradas-programadas")
	public String paradas(Model model) {
		Date output = Date.from(Instant.now());
		model.addAttribute("paradas", this.paradaService.findAllByFechaOrderByFecha(output));

		return "sede/paradas-programadas";
	}

}
