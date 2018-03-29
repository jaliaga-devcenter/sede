package teralco.sedeelectronica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.service.AnuncioService;
import teralco.sedeelectronica.utils.EncryptUtils;

@Controller
public class AnuncioController {

	private static String anuncios = "tablon-anuncios/tablon";

	private AnuncioService anuncioService;

	@Autowired
	private EncryptUtils encryptUtils;

	@Autowired
	public AnuncioController(AnuncioService pAnuncioService) {
		this.anuncioService = pAnuncioService;
	}

	@RequestMapping("/tablon-anuncios")
	public String actualidad(Model model) {
		model.addAttribute("anuncios", this.anuncioService.listAll());

		return anuncios;
	}

	@RequestMapping("/tablon-anuncios/{id}")
	public String detalle(@PathVariable Long id, Model model) {
		model.addAttribute("anuncioDetalle", this.anuncioService.get(id));
		model.addAttribute("encrypt", this.encryptUtils);

		return anuncios;
	}
}
