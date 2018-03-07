package teralco.sedeelectronica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AyudaController {

	@RequestMapping("/accesibilidad")
	public String accesibilidad() {
		return "accesibilidad";
	}

	@RequestMapping("/obtener-certificado")
	public String obtenerCertificado() {
		return "obtener-certificado-electronico";
	}

	@RequestMapping("/quejas-sugerencias")
	public String quejasSugerencias() {
		return "quejas-sugerencias";
	}

	@RequestMapping("/acceso")
	public String acceso() {
		return "acceso";
	}

	@RequestMapping("/instrucciones")
	public String instrucciones() {
		return "instrucciones";
	}

	@RequestMapping("/aviso-legal")
	public String avisoLegal() {
		return "aviso-legal";
	}

	@RequestMapping("/mapa-web")
	public String mapaWeb() {
		return "mapa-web";
	}

}