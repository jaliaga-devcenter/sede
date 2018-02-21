package teralco.sedeelectronica.utils;

import java.util.HashMap;
import java.util.Map;

public class RecaptchaUtil {

	public static final Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();
	static {
		RECAPTCHA_ERROR_CODE.put("missing-input-secret", "Error en la configuarion de la key secret de google.");
		RECAPTCHA_ERROR_CODE.put("invalid-input-secret", "La key secret de google es incorrecta.");
		RECAPTCHA_ERROR_CODE.put("missing-input-response", "Debe validar el captcha de google para continuar.");
		RECAPTCHA_ERROR_CODE.put("invalid-input-response", "La respuesta es inválida o tiene carácteres inválidos.");
		RECAPTCHA_ERROR_CODE.put("bad-request", "La solicitud es inválida.");
	}
}