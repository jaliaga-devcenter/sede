package teralco.sedeelectronica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarpetaCiudadanaController {

	@RequestMapping("/carpeta-ciudadana")
	public String carpetaCiudadana() {
		return "carpeta/carpeta-ciudadana";
	}

	@RequestMapping("/notifica")
	public String notifica() {
		return "carpeta/carpeta-ciudadana";
	}

}
