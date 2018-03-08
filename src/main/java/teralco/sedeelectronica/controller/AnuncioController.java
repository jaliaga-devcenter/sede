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

import teralco.sedeelectronica.model.Adjudicacion;
import teralco.sedeelectronica.model.Anuncio;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.AnuncioService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;
import teralco.sedeelectronica.utils.FicheroUtils;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class AnuncioController {

	private static String list = "tablon-anuncios/tablon";
	private static String redirList = "redirect:/tablon";
	private static String form = "tablon-anuncios/formAnuncios";
	private static String anuncios = "tablon-anuncios/tablon-anuncios";

	private AnuncioService anuncioService;
	private FicheroService ficheroService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AnuncioController(AnuncioService pAnuncioService, FicheroService pFicheroService) {
		this.anuncioService = pAnuncioService;
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/anuncios", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model, @PageableDefault(value = 10) Pageable pageable) {
		// DEVOLVER LA LISTA DE ADJUDICACIONES ACTUALES
		Page<Anuncio> pages = this.anuncioService.listAllByPage(pageable);
		model.addAttribute("adjudicaciones", pages);
		PageWrapper<Anuncio> page = new PageWrapper<>(pages, "/tablon-anuncios");
		model.addAttribute("page", page);

		model.addAttribute("encrypt", this.encryptUtils);
		return list;
	}

	@RequestMapping("/anuncios/create")
	public String create(Model model) {
		model.addAttribute("anuncio", new Adjudicacion());
		return form;
	}

	@RequestMapping("/anuncios/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("anuncio", this.anuncioService.get(id));
		return form;
	}

	@RequestMapping("/anuncios/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.anuncioService.delete(id);
		redirectAttrs.addFlashAttribute("message", "El anuncio " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/anuncios/save")
	public String save(@Valid @ModelAttribute("anuncio") Anuncio anuncio, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}

		Fichero file = FicheroUtils.convertirFichero(anuncio.getFileToUpload());
		if (file != null) {
			file = this.ficheroService.save(file);
			anuncio.setFichero(file);
		}

		this.anuncioService.save(anuncio);
		return redirList;
	}

	@RequestMapping("/tablon-anuncios")
	public String actualidad(Model model) {
		model.addAttribute("anuncios", this.anuncioService.list());

		return anuncios;
	}

	@RequestMapping("/tablon-anuncios/{id}")
	public String detalle(@PathVariable Long id, Model model) {
		model.addAttribute("anuncioDetalle", this.anuncioService.get(id));
		// actualidad.html
		return anuncios;
	}
}
