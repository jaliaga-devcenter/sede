package teralco.sedeelectronica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gexflow.wsdl.ConsultaCatalogoCategoriasResponse;
import teralco.sedeelectronica.gexflow.GexflowClient;

@Controller
public class HomeController {

	@Autowired
	private GexflowClient clienteWS;

	@RequestMapping("/")
	public String greeting(Model model) {
		model.addAttribute("name", "javi");
		return "index";
	}

	@RequestMapping("/tramites")
	public String tramites(Model model) {
		ConsultaCatalogoCategoriasResponse consultaCatalogoCategoriasResponse = this.clienteWS.getCategorias(0, "es");
		model.addAttribute("categorias",
				consultaCatalogoCategoriasResponse.getResultado().getCategorias().getCategoria());
		model.addAttribute("estado", consultaCatalogoCategoriasResponse.getResultado().getEstadoRespuesta());
		return "tramites";
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "perfil-del-contratante";
	}

}