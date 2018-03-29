package teralco.sedeelectronica.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
import teralco.sedeelectronica.model.DocumentacionLenguaje;
import teralco.sedeelectronica.model.Estado;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.DocumentacionService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminDocumentacionController {

	private static String list = "admin/documentos/documentos";
	private static String redirList = "redirect:/admin/documentos";
	private static String form = "admin/documentos/formDocumento";
	private static String STATE_STRING = "estados";
	private static String langModel = "langs";

	private DocumentacionService documentacionService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminDocumentacionController(DocumentacionService pDocumentacionService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.documentacionService = pDocumentacionService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/documentos", produces = "text/html;charset=UTF-8")
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

	@RequestMapping("/admin/documentos/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Documentacion docu = new Documentacion();
		lang.forEach(e -> docu.getTraducciones().add(new DocumentacionLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);
		model.addAttribute("documentacion", docu);
		model.addAttribute(STATE_STRING, Estado.values());
		return form;
	}

	@RequestMapping("/admin/documentos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Documentacion documentacion = this.documentacionService.get(id);

		List<String> source = new ArrayList<>();
		documentacion.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				documentacion.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				documentacion.getTraducciones().add(atomicInteger.get(), new DocumentacionLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("documentacion", documentacion);
		model.addAttribute(STATE_STRING, Estado.values());
		return form;
	}

	@RequestMapping("/admin/documentos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.documentacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La documentación " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/documentos/edit/", "/admin/documentos/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/documentos/save")
	public String save(@Valid @ModelAttribute("documentacion") Documentacion documentacion, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
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