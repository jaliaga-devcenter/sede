package teralco.sedeelectronica.utils;

import java.util.UUID;

public final class GeneradorUtils {

	public static String generarToken() {
		String token = UUID.randomUUID().toString();
		return token;
	}

}
