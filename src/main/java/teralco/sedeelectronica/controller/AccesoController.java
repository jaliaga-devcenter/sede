package teralco.sedeelectronica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccesoController {

	@RequestMapping("/acceso")
	public String accesibilidad() {
		return "acceso/acceso";
	}

	@RequestMapping("/login")
	public String login() {
		return "acceso/login";
	}

}