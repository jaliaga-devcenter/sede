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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.model.Modelo;
import teralco.sedeelectronica.model.ModeloLenguaje;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.service.ModeloService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AdminModeloController {

	private static String list = "admin/modelos/modelos";
	private static String redirList = "redirect:/admin/modelos";
	private static String form = "admin/modelos/formModelo";
	private static String langModel = "langs";

	private ModeloService modeloService;
	private LenguajeService lenguajeService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AdminModeloController(ModeloService pModeloService, LenguajeService pLenguajeService,
			FicheroService pFicheroService) {
		this.modeloService = pModeloService;
		this.lenguajeService = pLenguajeService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/admin/modelos", method = RequestMethod.GET)
	public String modelos(Model model, @PageableDefault(value = 10) Pageable pageable) {
		model.addAttribute("encrypt", this.encryptUtils);
		Page<Modelo> pages = this.modeloService.listAllByPage(pageable);
		model.addAttribute("modelos", pages);
		PageWrapper<Modelo> page = new PageWrapper<>(pages, "/modelos");
		model.addAttribute("page", page);

		return list;
	}

	@RequestMapping("/admin/modelos/create")
	public String create(Model model) {
		Iterable<Lenguaje> lang = this.lenguajeService.list();
		Modelo modelo = new Modelo();
		lang.forEach(e -> modelo.getTraducciones().add(new ModeloLenguaje(e.getCodigo())));
		model.addAttribute(langModel, lang);
		model.addAttribute("modelo", modelo);
		return form;
	}

	@RequestMapping("/admin/modelos/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		Iterable<Lenguaje> langs = this.lenguajeService.list();

		List<String> target = new ArrayList<>();
		langs.forEach(e -> target.add(e.getCodigo()));

		Modelo modelo = this.modeloService.get(id);

		List<String> source = new ArrayList<>();
		modelo.getTraducciones().forEach(e -> source.add(e.getIdioma()));
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// quitar
		source.forEach(s -> {
			if (!target.contains(s)) {
				modelo.getTraducciones().remove(atomicInteger.get());
			}
			atomicInteger.getAndIncrement();

		});

		atomicInteger.set(0);
		// anyadir
		target.forEach(s -> {
			if (!source.contains(s)) {
				modelo.getTraducciones().add(atomicInteger.get(), new ModeloLenguaje(s));

			}
			atomicInteger.getAndIncrement();

		});

		model.addAttribute(langModel, langs);
		model.addAttribute("modelo", modelo);
		return form;
	}

	@RequestMapping("/admin/modelos/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {

		this.modeloService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El modelo " + id + " ha sido borrado.");
		return redirList;
	}

	@RequestMapping({ "/admin/modelos/edit/", "/admin/modelos/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/modelos/save")
	public String save(@Valid @ModelAttribute("modelo") Modelo modelo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(langModel, this.lenguajeService.list());
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
