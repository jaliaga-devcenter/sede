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

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Medio;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.ModeloService;

@Controller
public class ModeloController {

	private ModeloService modeloService;
	private FicheroService ficheroService;

	@Autowired
	public ModeloController(ModeloService modeloService, FicheroService ficheroService) {
		this.modeloService = modeloService;
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/modelos", produces = "text/html;charset=UTF-8")
	public String modeloes(Model model) {
		// DEVOLVER LA LISTA DE modeloS ACTUALES
		model.addAttribute("modelos", modeloService.list());
		return "modelos/modelos";
	}

	@RequestMapping("/modelos/create")
	public String create(Model model) {
		model.addAttribute("modelo", new Modelo());
		return "modelos/formModelo";
	}

	@RequestMapping("/modelos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("modelo", modeloService.get(id));
		model.addAttribute("medios", Medio.values());
		return "modelos/formModelo";
	}

	@RequestMapping("/modelos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		modeloService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El modelo " + id + " ha sido borrado.");
		return "redirect:/modelos";
	}

	@PostMapping(value = "/modelos/save")
	public String save(@Valid @ModelAttribute("modelo") Modelo modelo, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("medios", Medio.values());
			return "modelos/formModelo";
		}
		Fichero file = ficheroService.guardarFichero(modelo.getFileToUpload());
		if (file != null) {
			modelo.setFichero(file);
		}
		modeloService.save(modelo);
		return "redirect:/modelos";
	}
}
