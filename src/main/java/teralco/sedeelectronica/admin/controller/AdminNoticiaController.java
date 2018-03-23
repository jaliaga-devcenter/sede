package teralco.sedeelectronica.admin.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
public class AdminNoticiaController {

	private static String list = "admin/noticias/noticias";
	private static String redirList = "redirect:/admin/noticias";
	private static String form = "admin/noticias/formNoticia";

	private NoticiaService noticiaService;

	@Autowired
	public AdminNoticiaController(NoticiaService pNoticiaService) {
		this.noticiaService = pNoticiaService;
	}

	@RequestMapping(value = "/admin/noticias", produces = "text/html;charset=UTF-8")
	public String noticias(Model model, @PageableDefault(value = 10) Pageable pageable) {
		Page<Noticia> pages = this.noticiaService.listAllByPage(pageable);
		model.addAttribute("noticias", pages);
		PageWrapper<Noticia> page = new PageWrapper<>(pages, "/noticias");
		model.addAttribute("page", page);

		return list;
	}

	@RequestMapping("/admin/noticias/create")
	public String create(Model model) {
		model.addAttribute("noticia", new Noticia());
		return form;
	}

	@RequestMapping("/admin/noticias/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("noticia", this.noticiaService.get(id));
		return form;
	}

	@RequestMapping("/admin/noticias/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		this.noticiaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La noticia " + id + " ha sido borrada.");
		return redirList;
	}

	@RequestMapping({ "/admin/noticias/edit/", "/admin/noticias/delete/" })
	public String noAction() {
		return "redirect:../";
	}

	@PostMapping(value = "/admin/noticias/save")
	public String save(@Valid @ModelAttribute("noticia") Noticia noticia, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form;
		}
		noticia.setFecha(Timestamp.valueOf(LocalDateTime.now()));
		this.noticiaService.save(noticia);
		return redirList;
	}

}
