package teralco.sedeelectronica.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teralco.sedeelectronica.model.Apertura;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Tipo;
import teralco.sedeelectronica.service.AperturaService;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.FicheroUtils;

@Controller
public class AperturaController {

	private AperturaService aperturaService;
	private FicheroService ficheroService;

	@Autowired
	public AperturaController(AperturaService aperturaService, FicheroService ficheroService) {
		this.aperturaService = aperturaService;
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/aperturas", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("aperturas", aperturaService.list());

		return "aperturas/aperturas";
	}

	@RequestMapping("/aperturas/create")
	public String create(Model model) {
		// DEVOLVER LA LISTA DE LICITACIONES ACTUALES
		model.addAttribute("apertura", new Apertura());
		return "aperturas/formApertura";
	}

	@RequestMapping("/aperturas/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("apertura", aperturaService.get(id));
		return "aperturas/formApertura";
	}

	@RequestMapping("/aperturas/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		aperturaService.delete(id);
		redirectAttrs.addFlashAttribute("message", "La apertura " + id + " ha sido borrada.");
		return "redirect:/aperturas";
	}

	@PostMapping(value = "/aperturas/save")
	public String save(@Valid @ModelAttribute("apertura") Apertura apertura, BindingResult bindingResult, Model model,
			RedirectAttributes redir) {
		String uuid = "";
		if (bindingResult.hasErrors()) {
			return "aperturas/formApertura";
		} else {
			if (apertura.getFileToUpload().getSize() > 0) {

				try {
					uuid = FicheroUtils.guardarFichero(apertura.getFileToUpload());
					/* save file in model */
					Fichero file = new Fichero();
					file.setNombre(apertura.getFileToUpload().getOriginalFilename());
					file.setUuid(uuid);
					file.setTipo(Tipo.pdf);
					file.setTamanyo((double) apertura.getFileToUpload().getSize());
					file = ficheroService.save(file);
					apertura.setResultado(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			aperturaService.save(apertura);

			return "redirect:/aperturas";
		}
	}

}
