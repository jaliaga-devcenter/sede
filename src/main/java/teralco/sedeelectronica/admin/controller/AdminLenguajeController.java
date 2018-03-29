package teralco.sedeelectronica.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.config.AjaxResponseBody;
import teralco.sedeelectronica.model.Lenguaje;
import teralco.sedeelectronica.service.LenguajeService;
import teralco.sedeelectronica.utils.Messages;

@Controller
public class AdminLenguajeController {

	private static String form = "admin/lenguajes/formlenguaje";

	@Autowired
	private Messages messages;

	private LenguajeService lenguajeService;

	@Autowired
	public AdminLenguajeController(LenguajeService pLenguajeService) {
		this.lenguajeService = pLenguajeService;
	}

	@RequestMapping(value = "/admin/lenguajes", produces = "text/html;charset=UTF-8")
	public String licitaciones(Model model) {
		model.addAttribute("langs", this.lenguajeService.list());
		return form;
	}

	@PostMapping(value = "/admin/lenguajes/create")
	public ResponseEntity<?> create(@RequestBody String[] langList) {
		AjaxResponseBody result = new AjaxResponseBody();
		if (langList.length == 0) {
			result.setMsg(this.messages.get("message.languages.error"));
			return ResponseEntity.badRequest().body(result);
		}
		this.lenguajeService.deleteAll();
		for (int i = 0; i < langList.length; i++) {
			String code = langList[i].substring(langList[i].indexOf(" - ") + 3);
			String idioma = langList[i].substring(0, langList[i].indexOf(" - "));
			Lenguaje lenguaje = new Lenguaje(code, idioma);

			this.lenguajeService.save(lenguaje);
		}
		result.setMsg("success");
		result.setData(langList);

		return ResponseEntity.ok(result);
	}
}
