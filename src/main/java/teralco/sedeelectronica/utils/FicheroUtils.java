package teralco.sedeelectronica.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import teralco.sedeelectronica.controller.HomeController;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Tipo;

@Component
public final class FicheroUtils {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private FicheroUtils() {
	}

	private static String serverUploadPath;

	@Autowired
	@Value("${server.uploadPath}")
	public static void testValue(String value) {
		serverUploadPath = value;
	}

	public static String guardarFichero(MultipartFile fichero) throws IOException {
		String uuidGenerado = GeneradorUtils.generarToken();

		byte[] bytes = fichero.getBytes();
		String ext = fichero.getOriginalFilename().substring(fichero.getOriginalFilename().lastIndexOf('.'));
		Path path = Paths.get(serverUploadPath + uuidGenerado + ext);

		Files.write(path, bytes);

		return uuidGenerado + ext;
	}

	public static Fichero convertirFichero(MultipartFile fichero) {
		Fichero file = null;
		if (fichero.getSize() > 0) {
			String uuid = "";
			try {
				uuid = FicheroUtils.guardarFichero(fichero);
				/* save file in model */
				file = new Fichero();
				file.setNombreOriginal(fichero.getOriginalFilename());
				file.setUuid(uuid);
				file.setTipo(Tipo.pdf);
				file.setTamanyo((double) fichero.getSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("LOG ERROR sedeelectronica IN FicheroUtils: " + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}

}