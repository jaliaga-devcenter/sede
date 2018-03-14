package teralco.sedeelectronica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.service.NoticiaService;

@Controller
public class NoticiaController {

	private static String actualidad = "noticias/actualidad";

	private NoticiaService noticiaService;

	@Autowired
	public NoticiaController(NoticiaService pNoticiaService) {
		this.noticiaService = pNoticiaService;
	}

	@RequestMapping("/actualidad")
	public String actualidad(Model model) {
		model.addAttribute("noticias", this.noticiaService.list());

		return actualidad;
	}

	@RequestMapping("/actualidad/{id}")
	public String detalle(@PathVariable Long id, Model model) {
		model.addAttribute("noticiaDetalle", this.noticiaService.get(id));
		return actualidad;
	}
}
