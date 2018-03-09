package teralco.sedeelectronica.midu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;

@Controller
public class MiduController {

	@RequestMapping("/loginKO")
	public String loginKO() {
		throw new SedeElectronicaException(ExceptionType.LOGIN_NO_OK);
	}

}