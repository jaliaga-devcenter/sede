package teralco.sedeelectronica.controller;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.service.ParadaService;

@Controller
public class ParadasController {

	private ParadaService paradaService;

	@Autowired
	public ParadasController(ParadaService pParadaService) {
		this.paradaService = pParadaService;
	}

	@RequestMapping("/paradas-programadas")
	public String paradas(Model model) {
		Date output = Date.from(Instant.now());
		model.addAttribute("paradas", this.paradaService.findAllByFechaOrderByFecha(output));

		return "sede/paradas-programadas";
	}

}
