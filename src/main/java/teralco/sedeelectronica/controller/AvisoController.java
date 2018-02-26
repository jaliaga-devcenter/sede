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

import teralco.sedeelectronica.model.Aviso;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.AvisoService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AvisoController {

	private AvisoService avisoService;
	private FicheroService ficheroService;

	@Autowired

	public AvisoController(AvisoService pAvisoService, FicheroService pFicheroService) {
		this.avisoService = pAvisoService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/avisos", produces = "text/html;charset=UTF-8")
	public String avisos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE AVISOS ACTUALES
		Page<Aviso> pages = this.avisoService.listAllByPage(pageable);
		model.addAttribute("aperturas", pages);
		PageWrapper<Aviso> page = new PageWrapper<Aviso>(pages, "/avisos");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", new EncryptUtils());
		return "avisos/avisos";
	}

	@RequestMapping("/avisos/create")
	public String create(Model model) {
		model.addAttribute("aviso", new Aviso());
		return "avisos/formAviso";
	}

	@RequestMapping("/avisos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("aviso", this.avisoService.get(id));
		return "avisos/formAviso";
	}

	@RequestMapping("/avisos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {

		this.avisoService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El aviso " + id + " ha sido borrado.");
		return "redirect:/avisos";
	}

	@PostMapping(value = "/avisos/save")

	public String save(@Valid @ModelAttribute("aviso") Aviso aviso, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "avisos/formAviso";
		}

		Fichero file = FicheroUtils.convertirFichero(aviso.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			aviso.setFichero(file);
		}
		this.avisoService.save(aviso);
		return "redirect:/avisos";
	}

}
