package teralco.sedeelectronica.captcha.utils;

import java.util.HashMap;
import java.util.Map;

public class RecaptchaUtil {

	private RecaptchaUtil() {

	}

	public static Map<String, String> getRecaptchaErrorCode() {
		return RECAPTCHA_ERROR_CODE;
	}

	private static final Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();

	static {
		getRecaptchaErrorCode().put("missing-input-secret", "Error en la configuarion de la key secret de google.");
		getRecaptchaErrorCode().put("invalid-input-secret", "La key secret de google es incorrecta.");
		getRecaptchaErrorCode().put("missing-input-response", "Debe validar el captcha de google para continuar.");
		getRecaptchaErrorCode().put("invalid-input-response", "La respuesta es inválida o tiene carácteres inválidos.");
		getRecaptchaErrorCode().put("bad-request", "La solicitud es inválida.");
	}
}