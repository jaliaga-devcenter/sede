package teralco.sedeelectronica.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.gexflow.client.GexflowClient;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.IconoDTO;
import teralco.sedeelectronica.gexflow.exception.GexflowWSException;

@Controller
public class HomeController {

	private static final String IDIOMA = "es";
	private static final Integer ENTIDAD = 0;
	@Autowired
	private GexflowClient clienteWS;

	@RequestMapping("/")
	public String greeting(Model model) {
		model.addAttribute("name", "javi");
		return "index";
	}

	@RequestMapping("/tramites")
	public String tramites(Model model) throws GexflowWSException {
		List<CategoriaDTO> categorias = this.clienteWS.getCategorias(ENTIDAD, IDIOMA);
		Map<Integer, IconoDTO> iconos = getIconosPorCategoria(categorias);

		model.addAttribute("categorias", categorias);
		model.addAttribute("iconos", iconos);

		return "tramites";
	}

	@RequestMapping("/perfil-del-contratante")
	public String perfilContratante() {
		return "perfil-del-contratante";
	}

	private Map<Integer, IconoDTO> getIconosPorCategoria(List<CategoriaDTO> categorias) {
		Map<Integer, IconoDTO> iconos = categorias.stream().map(categoria -> {
			try {
				return this.clienteWS.getIconoCategoria(ENTIDAD, IDIOMA, categoria.getIdCategoria());
			} catch (GexflowWSException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toMap(IconoDTO::getIdCategoria, icono -> icono));
		return iconos;
	}

}