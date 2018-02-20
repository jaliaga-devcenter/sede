package teralco.sedeelectronica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.model.Contacto;
import teralco.sedeelectronica.service.EmailService;

@Controller
public class ContactoController {

	private EmailService emailService;

	@Autowired
	public ContactoController(EmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping(value = "/contacto", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE ADJUDICACIONES ACTUALES
		model.addAttribute("contacto", new Contacto());
		return "contacto/formContacto";
	}

	@PostMapping(value = "/contacto/send")
	public String save(@Valid @ModelAttribute("contacto") Contacto contacto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "contacto/formContacto";
		}

		try {
			emailService.sendEmail(contacto);
		} catch (MailException | InterruptedException e) {
			model.addAttribute("message", "Ha ocurrido un error enviando el correo");
			return "contacto/formContacto";
		}

		return "/contacto/formContacto";
	}
}
