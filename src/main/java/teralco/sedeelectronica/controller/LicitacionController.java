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

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Licitacion;
import teralco.sedeelectronica.model.Medio;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LicitacionService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class LicitacionController {

	private static final String MEDIO_MODEL = "medios";

	private static String list = "licitaciones/licitaciones";
	private static String redirList = "redirect:/licitaciones";
	private static String form = "licitaciones/formLicitacion";

	private LicitacionService licitacionService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public LicitacionController(LicitacionService pLicitacionService, FicheroService pFicheroService) {
		this.licitacionService = pLicitacionService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/licitaciones", produces = "text/html;charset=UTF-8")

	public String licitaciones(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		Page<Licitacion> pages = this.licitacionService.listAllByPage(pageable);
		model.addAttribute("licitaciones", pages);
		PageWrapper<Licitacion> page = new PageWrapper<>(pages, "/licitaciones");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/licitaciones/create")
	public String create(Model model) {
		model.addAttribute("licitacion", new Licitacion());
		model.addAttribute(MEDIO_MODEL, Medio.values());
		return form;
	}

	@RequestMapping("/licitaciones/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("licitacion", this.licitacionService.get(id));
		model.addAttribute(MEDIO_MODEL, Medio.values());
		return form;
	}

	@RequestMapping("/licitaciones/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.licitacionService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La licitacion " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/licitaciones/save")
	public String save(@Valid @ModelAttribute("licitacion") Licitacion lici, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(MEDIO_MODEL, Medio.values());
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(lici.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			lici.setFichero(file);
		}
		this.licitacionService.save(lici);
		return redirList;
	}

}
