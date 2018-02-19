package teralco.sedeelectronica.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
		InputStream is = fichero.getInputStream();
		Files.copy(is, Paths.get(serverUploadPath + uuidGenerado), StandardCopyOption.REPLACE_EXISTING);

		Path path = Paths.get(serverUploadPath + uuidGenerado);

		Files.write(path, bytes);

		return uuidGenerado + "|||" + serverUploadPath;
	}
}
