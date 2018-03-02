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
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teralco.sedeelectronica.captcha.service.RecaptchaService;
import teralco.sedeelectronica.model.CSVValidation;
import teralco.sedeelectronica.verifirma.VerifirmaClient;

@Controller
public class VerifirmaController {

	private static final Integer ENTIDAD = 0;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private VerifirmaClient verifirmaClient;

	@Autowired
	private RecaptchaService captchaService;

	@RequestMapping("/verifirma")
	public String verifirma(Model model) {
		model.addAttribute("CSVValidation", new CSVValidation());
		return "verifirma/verifirma";
	}

	@PostMapping("/verifirma/send")
	public String signup(@Valid @ModelAttribute("CSVValidation") CSVValidation CSV, BindingResult bindingResult,
			Model model, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "verifirma/verifirma";
		}

		String ip = request.getRemoteAddr();

		String captchaVerifyMessage = this.captchaService.verifyRecaptcha(ip, recaptchaResponse);

		if (!captchaVerifyMessage.isEmpty()) {
			model.addAttribute("messageWarning", captchaVerifyMessage);
			return "verifirma/verifirma";
		}
		File fileDownload;
		try {
			/* envio de solicitud.. */
			fileDownload = this.verifirmaClient.obtenerDocumentoPorCvd(ENTIDAD, CSV.csv);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN VerifirmaController: " + e.getMessage());
			fileDownload = null;
			model.addAttribute("message", "Ha ocurrido un error en el servicio.");
			return "verifirma/verifirma";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN VerifirmaController: " + e.getMessage());
			model.addAttribute("message", "Ha ocurrido un error con el fichero.");
			fileDownload = null;
			return "verifirma/verifirma";
		}

		return "/verifirma/download/" + fileDownload;
	}

	@RequestMapping(value = "/verifirma/download/{file_name}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getFile(@PathVariable("file_name") File file, HttpServletResponse response) {
		try {

			// get your file as InputStream
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			response.setContentType("application/pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN VerifirmaController: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN VerifirmaController: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/*****************/
	/* FIN VERIFIRMA */
	/*****************/
}
