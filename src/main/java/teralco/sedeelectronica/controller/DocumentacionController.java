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

import teralco.sedeelectronica.model.Documentacion;
import teralco.sedeelectronica.model.Estado;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class DocumentacionController {

	private static String list = "adjudicaciones/adjudicaciones";
	private static String redirList = "redirect:/adjudicaciones";
	private static String form = "adjudicaciones/formAdjudicacion";
	private static String STATE_STRING = "estados";

	private DocumentacionService documentacionService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public DocumentacionController(DocumentacionService pDocumentacionService, FicheroService pFicheroService) {
		this.documentacionService = pDocumentacionService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/documentos", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE DOCUMENTOS ACTUALES
		Page<Documentacion> pages = this.documentacionService.listAllByPage(pageable);
		model.addAttribute("documentos", pages);
		PageWrapper<Documentacion> page = new PageWrapper<>(pages, "/documentos");
		model.addAttribute("page", page);
		model.addAttribute("", this.documentacionService.list());

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/documentos/create")
	public String create(Model model) {
		model.addAttribute("documentacion", new Documentacion());
		model.addAttribute(STATE_STRING, Estado.values());
		return form;
	}

	@RequestMapping("/documentos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("documentacion", this.documentacionService.get(id));
		model.addAttribute(STATE_STRING, Estado.values());
		return form;
	}

	@RequestMapping("/documentos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.documentacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La documentación " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/documentos/save")
	public String save(@Valid @ModelAttribute("documentacion") Documentacion documentacion, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(STATE_STRING, Estado.values());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(documentacion.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			documentacion.setFichero(file);
		}

		this.documentacionService.save(documentacion);
		return redirList;
	}
}