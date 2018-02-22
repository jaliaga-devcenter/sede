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
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;

@Controller
public class ModeloController {

	private ModeloService modeloService;
	private FicheroService ficheroService;

	@Autowired

	public ModeloController(ModeloService _modeloService, FicheroService _ficheroService) {
		this.modeloService = _modeloService;
		this.ficheroService = _ficheroService;
	}

	@RequestMapping(value = "/modelos", produces = "text/html;charset=UTF-8")
	public String modeloes(Model model) {
		// DEVOLVER LA LISTA DE modeloS ACTUALES

		model.addAttribute("modelos", this.modeloService.list());
		model.addAttribute("encrypt", new EncryptUtils());
		return "modelos/modelos";
	}

	@RequestMapping("/modelos/create")
	public String create(Model model) {
		model.addAttribute("modelo", new Modelo());
		return "modelos/formModelo";
	}

	@RequestMapping("/modelos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("modelo", this.modeloService.get(id));
		model.addAttribute("medios", Medio.values());
		return "modelos/formModelo";
	}

	@RequestMapping("/modelos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {

		this.modeloService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El modelo " + id + " ha sido borrado.");
		return "redirect:/modelos";
	}

	@PostMapping(value = "/modelos/save")
	public String save(@Valid @ModelAttribute("modelo") Modelo modelo, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("medios", Medio.values());
			return "modelos/formModelo";
		}

		Fichero file = FicheroUtils.convertirFichero(modelo.getFileToUpload());

		file = this.ficheroService.save(file);
		if (file != null) {
			modelo.setFichero(file);
		}


		this.modeloService.save(modelo);
		return "redirect:/modelos";
	}
}