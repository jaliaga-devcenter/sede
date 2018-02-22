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

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;

@Controller
public class AperturaController {

	private AperturaService aperturaService;
	private FicheroService ficheroService;

	@Autowired
	public AperturaController(AperturaService _aperturaService, FicheroService _ficheroService) {
		this.aperturaService = _aperturaService;
		this.ficheroService = _ficheroService;
	}

	@RequestMapping(value = "/aperturas", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE APERTURAS ACTUALES
		model.addAttribute("aperturas", this.aperturaService.list());
		model.addAttribute("encrypt", new EncryptUtils());
		return "aperturas/aperturas";
	}

	@RequestMapping("/aperturas/create")
	public String create(Model model) {
		model.addAttribute("apertura", new Apertura());
		return "aperturas/formApertura";
	}

	@RequestMapping("/aperturas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("apertura", this.aperturaService.get(id));
		return "aperturas/formApertura";
	}

	@RequestMapping("/aperturas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.aperturaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La apertura " + id + " ha sido borrada.");
		return "redirect:/aperturas";
	}

	@PostMapping(value = "/aperturas/save")
	public String save(@Valid @ModelAttribute("apertura") Apertura apertura, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "aperturas/formApertura";
		}

		Fichero file = FicheroUtils.convertirFichero(apertura.getFileToUpload());
		file = this.ficheroService.save(file);
		if (file != null) {
			apertura.setResultado(file);
		}

		this.aperturaService.save(apertura);

		return "redirect:/aperturas";
	}
}