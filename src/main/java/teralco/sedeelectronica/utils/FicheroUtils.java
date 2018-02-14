package teralco.sedeelectronica.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public final class FicheroUtils {

	private FicheroUtils() {

	}

	@Value("${server.uploadPath}")
	private static String UPLOADED_FOLDER;

	public static String guardarFichero(MultipartFile fichero) throws IOException {
		String uuidGenerado = GeneradorUtils.generarToken();

		byte[] bytes = fichero.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + uuidGenerado);
		Files.write(path, bytes);

		return uuidGenerado;
	}
}
