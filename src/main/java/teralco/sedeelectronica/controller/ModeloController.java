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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Medio;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.ModeloService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class ModeloController {

	private static String list = "modelos/modelos";
	private static String redirList = "redirect:/modelos";
	private static String form = "modelos/formModelo";

	private ModeloService modeloService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public ModeloController(ModeloService pModeloService, FicheroService pFicheroService) {
		this.modeloService = pModeloService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/modelos", method = RequestMethod.GET)
	public String modeloes(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE MODELOS ACTUALES
		model.addAttribute("encrypt", this.encryptUtils);
		Page<Modelo> pages = this.modeloService.listAllByPage(pageable);
		model.addAttribute("modelos", pages);
		PageWrapper<Modelo> page = new PageWrapper<>(pages, "/modelos");
		model.addAttribute("page", page);

		return list;
	}

	@RequestMapping("/modelos/create")
	public String create(Model model) {
		model.addAttribute("modelo", new Modelo());
		return form;
	}

	@RequestMapping("/modelos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("modelo", this.modeloService.get(id));
		model.addAttribute("medios", Medio.values());
		return form;
	}

	@RequestMapping("/modelos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {

		this.modeloService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El modelo " + id + " ha sido borrado.");
		return redirList;
	}

	@PostMapping(value = "/modelos/save")
	public String save(@Valid @ModelAttribute("modelo") Modelo modelo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("medios", Medio.values());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(modelo.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			modelo.setFichero(file);
		}
		this.modeloService.save(modelo);
		return redirList;
	}
}
