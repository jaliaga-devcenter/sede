package teralco.sedeelectronica.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.service.FicheroService;
import teralco.sedeelectronica.utils.EncryptUtils;

@Controller
public class DownloadController {

	private FicheroService ficheroService;
	@Value("${server.uploadPath}")
	private String serverUploadPath;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public DownloadController(FicheroService pFicheroService) {
		this.ficheroService = pFicheroService;
	}

	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
	public ResponseEntity<Resource> getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
		try {

			Fichero file = this.ficheroService.get(Long.decode(EncryptUtils.decrypt(fileName)));
			// get your file as InputStream
			Path path = Paths.get(this.serverUploadPath + file.getUuid());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			response.setContentType("application/pdf");
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getNombreOriginal());
			return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
					.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN DownloadController: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("LOG ERROR sedeelectronica IN DownloadController: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
