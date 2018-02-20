package teralco.sedeelectronica.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	public DownloadController(FicheroService ficheroService) {
		this.ficheroService = ficheroService;
	}

	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.GET)
	public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
		try {
			Fichero file = ficheroService.get(Long.decode(EncryptUtils.decrypt(fileName)));
			// get your file as InputStream
			InputStream is = new FileInputStream(serverUploadPath + file.getUuid());
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment:filename=" + file.getNombreOriginal());
			// copy it to response's OutputStream
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
