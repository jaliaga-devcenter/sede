package teralco.sedeelectronica.admin.controller;

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
public class AdminNormativaController {

	private static String list = "admin/normativa/normativas";
	private static String redirList = "redirect:/admin/normativa";
	private static String form = "admin/normativa/formNormativa";

	private NormativaService normativaService;

	@Autowired
	public AdminNormativaController(NormativaService pNormativaService) {
		this.normativaService = pNormativaService;
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
		model.addAttribute("normativa", new Normativa());
		return form;
	}

	@RequestMapping("/admin/normativa/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("normativa", this.normativaService.get(id));
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
	public String save(@Valid @ModelAttribute("normativa") Normativa normativa, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}

		this.normativaService.save(normativa);
		return redirList;
	}
}
