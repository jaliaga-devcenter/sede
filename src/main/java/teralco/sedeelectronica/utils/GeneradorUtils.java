package teralco.sedeelectronica.utils;

import java.util.UUID;

public final class GeneradorUtils {

	private GeneradorUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String generarToken() {
		return UUID.randomUUID().toString();
	}

}
