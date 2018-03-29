package teralco.sedeelectronica.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		this.accessor = new MessageSourceAccessor(this.messageSource, LocaleContextHolder.getLocale());
	}

	public String get(String code) {
		return this.accessor.getMessage(code);
	}

}