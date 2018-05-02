package teralco.sedeelectronica.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.model.Fichero;
import teralco.sedeelectronica.model.Tipo;

@Component
public final class FicheroUtils {

	private FicheroUtils() {
	}

	private static String serverUploadPath;

	@SuppressWarnings("static-method")
	@Autowired
	@Value("${server.uploadPath}")
	public void setServerUploadPath(String uploadPath) {
		serverUploadPath = uploadPath;
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
		if (fichero != null && fichero.getSize() > 0) {
			String uuid = "";
			try {
				uuid = FicheroUtils.guardarFichero(fichero);

			} catch (IOException e) {
				throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
			}
			/* save file in model */
			file = new Fichero();
			file.setNombreOriginal(fichero.getOriginalFilename());
			file.setUuid(uuid);
			file.setTipo(Tipo.PDF);
			file.setTamanyo((double) fichero.getSize());
		}
		return file;
	}

}
