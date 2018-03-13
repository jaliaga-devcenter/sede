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

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminAperturaController {

	private static String list = "admin/aperturas/aperturas";
	private static String redirList = "redirect:/admin/aperturas";
	private static String form = "admin/aperturas/formApertura";

	private AperturaService aperturaService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminAperturaController(AperturaService pAperturaService, FicheroService pFicheroService) {
		this.aperturaService = pAperturaService;
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
		model.addAttribute("apertura", new Apertura());
		return form;
	}

	@RequestMapping("/admin/aperturas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("apertura", this.aperturaService.get(id));
		return form;
	}

	@RequestMapping("/admin/aperturas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.aperturaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La apertura " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/admin/aperturas/save")
	public String save(@Valid @ModelAttribute("apertura") Apertura apertura, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
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
