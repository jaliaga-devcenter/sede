package teralco.sedeelectronica.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Tipo;
import teralco.sedeelectronica.service.FicheroService;

@Component
public final class FicheroUtils {

	private FicheroUtils() {

	}

	private static String serverUploadPath;

	@Autowired
	@Value("${server.uploadPath}")
	public void testValue(String value) {
		serverUploadPath = value;
	}

	public static String guardarFichero(MultipartFile fichero) throws IOException {
		String uuidGenerado = GeneradorUtils.generarToken();

		byte[] bytes = fichero.getBytes();

		Path path = Paths.get(serverUploadPath + uuidGenerado);

		Files.write(path, bytes);

		return uuidGenerado;
	}

	public static Fichero guardarFicheroBD(MultipartFile fichero, FicheroService ficheroService) {
		Fichero file = null;
		if (fichero.getSize() > 0) {
			String uuid = "";
			try {
				uuid = FicheroUtils.guardarFichero(fichero);
				/* save file in model */
				file = new Fichero();
				file.setNombre(fichero.getOriginalFilename());
				file.setUuid(uuid);
				file.setTipo(Tipo.pdf);
				file.setTamanyo((double) fichero.getSize());
				file = ficheroService.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
}
