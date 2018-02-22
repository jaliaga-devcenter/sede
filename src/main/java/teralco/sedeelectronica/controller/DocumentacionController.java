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

import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Estado;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;

@Controller
public class DocumentacionController {

	private DocumentacionService documentacionService;
	private FicheroService ficheroService;

	@Autowired
	public DocumentacionController(DocumentacionService _documentacionService, FicheroService _ficheroService) {
		this.documentacionService = _documentacionService;
		this.ficheroService = _ficheroService;
	}

	@RequestMapping(value = "/documentos", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("documentaciones", this.documentacionService.list());
		model.addAttribute("encrypt", new EncryptUtils());
		return "documentos/documentos";
	}

	@RequestMapping("/documentos/create")
	public String create(Model model) {
		model.addAttribute("documentacion", new Documentacion());
		model.addAttribute("estados", Estado.values());
		return "documentos/formDocumento";
	}

	@RequestMapping("/documentos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("documentacion", this.documentacionService.get(id));
		model.addAttribute("estados", Estado.values());
		return "documentos/formDocumento";
	}

	@RequestMapping("/documentos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.documentacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La documentación " + id + " ha sido borrada.");
		return "redirect:/documentos";
	}

	@PostMapping(value = "/documentos/save")
	public String save(@Valid @ModelAttribute("documentacion") Documentacion documentacion, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("estados", Estado.values());
			return "documentos/formDocumento";
		}

		Fichero file = FicheroUtils.convertirFichero(documentacion.getFileToUpload());
		file = this.ficheroService.save(file);
		if (file != null) {
			documentacion.setFichero(file);
		}

		this.documentacionService.save(documentacion);

		return "redirect:/documentos";
	}
}