package teralco.sedeelectronica.controller;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.auth.client.AuthClient;
import teralco.sedeelectronica.auth.dto.DiaFestivoDTO;
import teralco.sedeelectronica.utils.LanguageUtils;

@Controller
public class ConoceSedeController {

	@Autowired
	private AuthClient authClient;

	@Value("${sede.entidad}")
	private Integer entidad;

	@RequestMapping("/conoce-la-sede-electronica")
	public String conceLaSede() {
		return "redirect:./normativa-sobre-la-sede";
	}

	@RequestMapping("/contenidos-de-la-sede")
	public String contenidosSede() {
		return "sede/contenidos-de-la-sede";
	}

	@RequestMapping("/servicios-de-la-sede")
	public String serviciosSede() {
		return "sede/servicios-de-la-sede";
	}

	@RequestMapping("/registro-electronico")
	public String registro() {
		return "sede/registro-electronico";
	}

	@RequestMapping("/firma-electronica-y-certificados-admitidos")
	public String firmaYcertificados() {
		return "sede/firma-electronica-y-certificados-admitidos";
	}

	@RequestMapping("/fecha-y-hora-oficial")
	public String fechaYhora(Model model) {
		LocalDateTime now = LocalDateTime.now();
		LocalTime time = LocalTime.now();
		model.addAttribute("fecha", now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
		model.addAttribute("hora",
				time.format(new DateTimeFormatterBuilder().appendValue(HOUR_OF_DAY, 2).appendLiteral(':')
						.appendValue(MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':')
						.appendValue(SECOND_OF_MINUTE, 2).toFormatter()));
		return "sede/fecha-y-hora-oficial";
	}

	@RequestMapping("/calendario-dias-inhabiles")
	public String calendario(Model model) {
		List<DiaFestivoDTO> calendario = this.authClient.getCalendario(this.entidad, LanguageUtils.getLanguage(),
				LocalDate.now().getYear());
		model.addAttribute("calendario", calendario);
		return "sede/calendario-dias-inhabiles";
	}

	@RequestMapping("/sedes-fisicas")
	public String sedes() {
		return "sede/sedes-fisicas";
	}

}
