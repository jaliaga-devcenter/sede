package teralco.sedeelectronica.utils;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LanguageUtils {

	private LanguageUtils() {

	}

	public static String SPANISH = "es";

	public static String getLanguage() {
		String language = LocaleContextHolder.getLocale().getLanguage();
		if (!SPANISH.equals(language)) {
			return SPANISH;
		}

		return language;
	}
}
