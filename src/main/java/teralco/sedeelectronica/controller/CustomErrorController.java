package teralco.sedeelectronica.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;

@Controller
public class CustomErrorController implements ErrorController {

	// private static Logger LOGGER =
	// LoggerFactory.getLogger(CustomErrorController.class);

	private static final String ERROR = "error";
	private static final String ERROR_PATH = "/error";
	private static final String ERROR_TEMPLATE = "error";

	private final ErrorAttributes errorAttributes;

	@Autowired
	public CustomErrorController(ErrorAttributes pErrorAttributes) {
		this.errorAttributes = pErrorAttributes;
	}

	@RequestMapping(ERROR_PATH)
	public String error(Model model, HttpServletRequest request) {
		Map<String, Object> error = getErrorAttributes(request, true);
		model.addAttribute("timestamp", error.get("timestamp"));
		model.addAttribute("status", error.get("status"));
		model.addAttribute(ERROR, error.get(ERROR));
		model.addAttribute("message", error.get("message"));
		model.addAttribute("path", error.get("path"));
		return ERROR_TEMPLATE;
	}

	@RequestMapping("/loginKO")
	public String loginKO() {
		throw new SedeElectronicaException(ExceptionType.LOGIN_NO_OK);
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}
}