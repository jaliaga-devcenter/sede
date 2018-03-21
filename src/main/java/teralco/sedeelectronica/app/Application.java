package teralco.sedeelectronica.app;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import teralco.sedeelectronica.security.interceptor.ProcedimientoHandlerInterceptorAdapter;
import teralco.sedeelectronica.utils.LanguageUtils;

@SpringBootApplication
@ComponentScan(basePackages = "teralco.sedeelectronica")
@EntityScan("teralco.sedeelectronica.model")
@EnableJpaRepositories("teralco.sedeelectronica.repository")
@EnableAsync
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.forLanguageTag(LanguageUtils.SPANISH));
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	public ProcedimientoHandlerInterceptorAdapter procedimientoInterceptor() {
		return new ProcedimientoHandlerInterceptorAdapter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(procedimientoInterceptor()).addPathPatterns("/procedimiento", "/procedimiento/**",
				"/carpeta-ciudadana");
	}

}