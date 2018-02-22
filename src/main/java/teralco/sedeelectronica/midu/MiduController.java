package teralco.sedeelectronica.midu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MiduController {

	@RequestMapping("/loginOK")
	public String loginOK(@RequestParam(name = "salida_json") String usuarioJson, Model model) {
		model.addAttribute("usuario", usuarioJson);
		return "redirect:/";
	}

	@RequestMapping("/loginKO")
	public String loginKO(Model model) {
		model.addAttribute("name", "javi");
		return "redirect:/";
	}

}