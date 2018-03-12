package teralco.sedeelectronica.controller;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public abstract class LocaleController {

	protected String idioma = "es";

	protected Locale locale;

	public LocaleController() {
		this.locale = LocaleContextHolder.getLocale();
		this.idioma = this.locale.getLanguage();
	}
}
