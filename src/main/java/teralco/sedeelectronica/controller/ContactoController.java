package teralco.sedeelectronica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teralco.sedeelectronica.captcha.service.RecaptchaService;
import teralco.sedeelectronica.model.Contacto;
import teralco.sedeelectronica.service.EmailService;

@Controller
public class ContactoController {

	@Autowired
	private RecaptchaService captchaService;

	private EmailService emailService;

	@Autowired

	public ContactoController(EmailService _emailService) {
		this.emailService = _emailService;
	}

	@RequestMapping(value = "/contacto", produces = "text/html;charset=UTF-8")
	public String aperturas(Model model) {
		// DEVOLVER LA LISTA DE ADJUDICACIONES ACTUALES
		model.addAttribute("contacto", new Contacto());
		return "contacto/formContacto";
	}

	/*
	 * @PostMapping(value = "/contacto/send")
	 * 
	 * @ResponseBody public String save(@Valid @ModelAttribute("contacto") Contacto
	 * contacto, BindingResult bindingResult, Model model, HttpServletRequest
	 * request) {
	 * 
	 * String response = request.getParameter("g-recaptcha-response");
	 * captchaService.processResponse(response);
	 * 
	 * if (bindingResult.hasErrors()) { return "contacto/formContacto"; }
	 * 
	 * try { emailService.sendEmail(contacto); } catch (MailException |
	 * InterruptedException e) { model.addAttribute("message",
	 * "Ha ocurrido un error enviando el correo"); return "contacto/formContacto"; }
	 * 
	 * return "/contacto/formContacto"; }
	 */

	@PostMapping("/contacto/send")
	public String signup(@Valid @ModelAttribute("contacto") Contacto contacto, BindingResult bindingResult, Model model,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "contacto/formContacto";
		}

		String ip = request.getRemoteAddr();

		String captchaVerifyMessage = this.captchaService.verifyRecaptcha(ip, recaptchaResponse);

		if (!captchaVerifyMessage.isEmpty()) {
			model.addAttribute("messageWarning", captchaVerifyMessage);
			return "contacto/formContacto";
		}

		try {

			this.emailService.sendEmail(contacto);
		} catch (@SuppressWarnings("unused") MailException e) {
			model.addAttribute("message", "Ha ocurrido un error enviando el correo");
			return "contacto/formContacto";
		}

		return "/contacto/formContacto";
	}

}
