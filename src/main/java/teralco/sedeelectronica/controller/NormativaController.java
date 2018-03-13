package teralco.sedeelectronica.controller;

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

import teralco.sedeelectronica.model.Normativa;
import teralco.sedeelectronica.service.NormativaService;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class NormativaController {

	private static String list = "normativa/normativas";
	private static String redirList = "redirect:/normativa";
	private static String form = "normativa/formNormativa";
	private static String normas = "normativa/normativa-sobre-la-sede";

	private NormativaService normativaService;

	@Autowired
	public NormativaController(NormativaService pNormativaService) {
		this.normativaService = pNormativaService;
	}

	@RequestMapping(value = "/normativa-sobre-la-sede", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE NORMATIVAS
		model.addAttribute("normativas", this.normativaService.list());
		return normas;
	}

	@RequestMapping(value = "/normativa", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE ADJUDICACIONES ACTUALES
		Page<Normativa> pages = this.normativaService.listAllByPage(pageable);
		model.addAttribute("normativas", pages);
		PageWrapper<Normativa> page = new PageWrapper<>(pages, "/normativa");
		model.addAttribute("page", page);
		return list;
	}

	@RequestMapping("/normativa/create")
	public String create(Model model) {
		model.addAttribute("normativa", new Normativa());
		return form;
	}

	@RequestMapping("/normativa/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("normativa", this.normativaService.get(id));
		return form;
	}

	@RequestMapping("/normativa/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.normativaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La normativa " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/normativa/save")
	public String save(@Valid @ModelAttribute("normativa") Normativa normativa, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}

		this.normativaService.save(normativa);
		return redirList;
	}
}
