package teralco.sedeelectronica.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teralco.sedeelectronica.captcha.service.RecaptchaService;
import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.model.CSVValidation;
import teralco.sedeelectronica.verifirma.VerifirmaClient;

@Controller
public class VerifirmaController {

	private static String verify = "verifirma/verifirma";

	private static final Logger LOGGER = LoggerFactory.getLogger(VerifirmaController.class);

	private static final Integer ENTIDAD = 0;

	@Autowired
	private VerifirmaClient verifirmaClient;

	@Autowired
	private RecaptchaService captchaService;

	@RequestMapping("/verifirma")
	public String verifirma(Model model) {
		model.addAttribute("CSVValidation", new CSVValidation());
		return verify;
	}

	@PostMapping("/verifirma/send")
	public Object signup(@Valid @ModelAttribute("CSVValidation") CSVValidation csv, BindingResult bindingResult,
			Model model, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			HttpServletRequest request, HttpServletResponse response) {

		if (bindingResult.hasErrors()) {
			return verify;
		}

		String ip = request.getRemoteAddr();

		String captchaVerifyMessage = this.captchaService.verifyRecaptcha(ip, recaptchaResponse);

		if (!captchaVerifyMessage.isEmpty()) {
			model.addAttribute("messageWarning", captchaVerifyMessage);
			return verify;
		}
		File fileDownload;
		try {
			fileDownload = this.verifirmaClient.obtenerDocumentoPorCvd(ENTIDAD, csv.csv);
		} catch (ServiceException e) {

			LOGGER.error("ERROR CONTROLADO", e);
			model.addAttribute("message", "Ha ocurrido un error en el servicio.");
			return verify;
		} catch (IOException e) {

			LOGGER.error("ERROR CONTROLADO", e);
			model.addAttribute("message", "Ha ocurrido un error con el fichero.");
			return verify;
		}

		try {
			Path path = Paths.get(fileDownload.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			response.setContentType("application/pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileDownload.getName());
			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);

		} catch (IOException | NumberFormatException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

	}

}
