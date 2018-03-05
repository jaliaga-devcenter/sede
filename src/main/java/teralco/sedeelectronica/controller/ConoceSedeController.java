package teralco.sedeelectronica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.model.CSVValidation;

@Controller
public class ConoceSedeController {

	@RequestMapping("/conoce-la-sede-electronica")
	public String conceLaSede() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/conoce-la-sede-electronica";
	}

	@RequestMapping("/normativa-sobre-la-sede")
	public String normativaSede() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/normativa-sobre-la-sede";
	}

	@RequestMapping("/contenidos-de-la-sede")
	public String contenidosSede() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/contenidos-de-la-sede";
	}

	@RequestMapping("/servicios-de-la-sede")
	public String serviciosSede() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/servicios-de-la-sede";
	}

	@RequestMapping("/registro-electronico")
	public String registro() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/registro-electronico";
	}

	@RequestMapping("/firma-electronica-y-certificados-admitidos")
	public String firmaYcertificados() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/firma-electronica-y-certificados-admitidos";
	}

	@RequestMapping("/verificacion-de-documentos")
	public String verificacion(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("CSVValidation", new CSVValidation());
		return "verifirma/verifirma";
	}

	@RequestMapping("/fecha-y-hora-oficial")
	public String fechaYhora() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/fecha-y-hora-oficial";
	}

	@RequestMapping("/calendario-dias-inhabiles")
	public String calendario() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/calendario-dias-inhabiles";
	}

	@RequestMapping("/sedes-fisicas")
	public String sedes() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/sedes-fisicas";
	}

	@RequestMapping("/paradas-programadas")
	public String paradas() {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		return "sede/paradas-programadas";
	}
	
}
