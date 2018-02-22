package teralco.sedeelectronica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class AvisoController {

	private AvisoService avisoService;
	private FicheroService ficheroService;

	@Autowired
	public AvisoController(AvisoService avisoService, FicheroService ficheroService) {
		this.avisoService = avisoService;
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/avisos", produces = "text/html;charset=UTF-8")
	public String avisos(Model model) {
		// DEVOLVER LA LISTA DE avisos ACTUALES
		model.addAttribute("avisos", avisoService.list());
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
		model.addAttribute("aviso", avisoService.get(id));
		return "avisos/formAviso";
	}

	@RequestMapping("/avisos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		avisoService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El aviso " + id + " ha sido borrado.");
		return "redirect:/avisos";
	}

	@PostMapping(value = "/avisos/save")
	public String save(@Valid @ModelAttribute("aviso") Aviso aviso, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "avisos/formAviso";
		}

		Fichero file = FicheroUtils.convertirFichero(aviso.getFileToUpload());
		file = ficheroService.save(file);
		if (file != null) {
			aviso.setFichero(file);
		}

		avisoService.save(aviso);
		return "redirect:/avisos";
	}

}
