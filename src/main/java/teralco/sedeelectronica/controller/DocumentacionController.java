package teralco.sedeelectronica.controller;

import java.io.IOException;

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
import teralco.sedeelectronica.model.Tipo;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.FicheroUtils;

@Controller
public class DocumentacionController {

	private DocumentacionService documentacionService;
	private FicheroService ficheroService;

	@Autowired
	public DocumentacionController(DocumentacionService documentacionService, FicheroService ficheroService) {
		this.documentacionService = documentacionService;
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/documentos", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("documentaciones", documentacionService.list());

		return "documentos/documentos";
	}

	@RequestMapping("/documentos/create")
	public String create(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("documentacion", new Documentacion());
		model.addAttribute("estados", Estado.values());
		return "documentos/formDocumento";
	}

	@RequestMapping("/documentos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("documentacion", documentacionService.get(id));
		model.addAttribute("estados", Estado.values());
		return "documentos/formDocumento";
	}

	@RequestMapping("/documentos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		documentacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La documentación " + id + " ha sido borrada.");
		return "redirect:/documentos";
	}

	@PostMapping(value = "/documentos/save")
	public String save(@Valid @ModelAttribute("documentacion") Documentacion documentacion, BindingResult bindingResult,
			Model model, RedirectAttributes redir) {
		String uuid = "";
		if (bindingResult.hasErrors()) {
			model.addAttribute("estados", Estado.values());
			return "documentos/formDocumento";
		} else {
			if (documentacion.getFileToUpload().getSize() > 0) {

				try {
					uuid = FicheroUtils.guardarFichero(documentacion.getFileToUpload());
					/* save file in model */
					Fichero file = new Fichero();
					file.setNombre(documentacion.getFileToUpload().getOriginalFilename());
					file.setUuid(uuid);
					file.setTipo(Tipo.pdf);
					file.setTamanyo((double) documentacion.getFileToUpload().getSize());
					file = ficheroService.save(file);
					documentacion.setFichero(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			documentacionService.save(documentacion);

			return "redirect:/documentos";
		}
	}

}
