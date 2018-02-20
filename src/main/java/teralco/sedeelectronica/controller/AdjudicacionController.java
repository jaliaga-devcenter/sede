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

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.AdjudicacionService;
import teralco.sedeelectronica.service.FicheroService;

@Controller
public class AdjudicacionController {

	private AdjudicacionService adjudicacionService;
	private FicheroService ficheroService;

	@Autowired
	public AdjudicacionController(AdjudicacionService adjudicacionService, FicheroService ficheroService) {
		this.adjudicacionService = adjudicacionService;
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/adjudicaciones", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE ADJUDICACIONES ACTUALES
		model.addAttribute("adjudicaciones", adjudicacionService.list());

		return "adjudicaciones/adjudicaciones";
	}

	@RequestMapping("/adjudicaciones/create")
	public String create(Model model) {
		model.addAttribute("adjudicacion", new Adjudicacion());
		return "adjudicaciones/formAdjudicacion";
	}

	@RequestMapping("/adjudicaciones/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("adjudicacion", adjudicacionService.get(id));
		return "adjudicaciones/formAdjudicacion";
	}

	@RequestMapping("/adjudicaciones/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		adjudicacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La adjudicación " + id + " ha sido borrada.");
		return "redirect:/adjudicaciones";
	}

	@PostMapping(value = "/adjudicaciones/save")
	public String save(@Valid @ModelAttribute("adjudicacion") Adjudicacion adjudicacion, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "adjudicaciones/formAdjudicacion";
		}
		Fichero file = ficheroService.guardarFichero(adjudicacion.getFileToUpload());
		if (file != null) {
			adjudicacion.setResultado(file);
		}
		adjudicacionService.save(adjudicacion);
		return "redirect:/adjudicaciones";
	}
}
