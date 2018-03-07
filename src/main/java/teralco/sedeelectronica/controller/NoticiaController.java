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

import teralco.sedeelectronica.model.Noticia;
import teralco.sedeelectronica.service.NoticiaService;
import teralco.sedeelectronica.utils.PageWrapper;

@Controller
public class NoticiaController {

	private static String list = "noticias/noticias";
	private static String redirList = "redirect:/noticias";
	private static String form = "noticias/formNoticia";
	private static String actualidad = "noticias/actualidad";

	private NoticiaService noticiaService;

	@Autowired
	public NoticiaController(NoticiaService pNoticiaService) {
		this.noticiaService = pNoticiaService;
	}

	@RequestMapping(value = "/noticias", produces = "text/html;charset=UTF-8")
	public String noticias(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Noticia> pages = this.noticiaService.listAllByPage(pageable);
		model.addAttribute("noticias", pages);
		PageWrapper<Noticia> page = new PageWrapper<>(pages, "/noticias");
		model.addAttribute("page", page);

		return list;
	}

	@RequestMapping("/noticias/create")
	public String create(Model model) {
		model.addAttribute("noticia", new Noticia());
		return form;
	}

	@RequestMapping("/noticias/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("noticia", this.noticiaService.get(id));
		return form;
	}

	@RequestMapping("/noticias/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.noticiaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La noticia " + id + " ha sido borrada.");
		return redirList;
	}

	@PostMapping(value = "/noticias/save")
	public String save(@Valid @ModelAttribute("notica") Noticia noticia, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}

		this.noticiaService.save(noticia);
		return redirList;
	}

	@RequestMapping("/actualidad")
	public String actualidad() {
		// Listado de noticias usando el template que hay en noticias/actualidad.html
		return actualidad;
	}

	@RequestMapping("/actualidad/{id}")
	public String detalle() {
		// Detalle de la noticia seleccionada. Crear un nuevo template a partir del
		// actualidad.html
		return actualidad;
	}
}
