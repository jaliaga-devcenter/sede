package teralco.sedeelectronica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.service.NormativaService;

@Controller
public class NormativaController {

	private static String normas = "normativa/normativa-sobre-la-sede";

	private NormativaService normativaService;

	@Autowired
	public NormativaController(NormativaService pNormativaService) {
		this.normativaService = pNormativaService;
	}

	@RequestMapping(value = "/normativa-sobre-la-sede", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		model.addAttribute("normativas", this.normativaService.list());
		return normas;
	}

}
