package teralco.sedeelectronica.midu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import teralco.sedeelectronica.security.UsuarioSede;

@Controller
public class MiduController {

	private static Logger LOGGER = LoggerFactory.getLogger(MiduController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	// http://localhost:8081/sede/loginOK?salida_json=-d4_qpz21LcqhVp5Q8tyGGPHrYYc1zyaU5WzbYcv4u6nnF9cKMbdrQksqKJYgitGhApOqjSwBKkH%20%20i-C1xT2zFTh8GJBXQNZ5TB7VI_ptk-nFO2ABfq3DYfPlv1N3CHHkjRJYIZY6E2bQhE36avXRgBPG%20%20SHHa3MWXxx53COuRW9UYWs03c8Uvu7gJzsHeO8R7RcOtrEHDBzmfpo-B0kiNP-Kc8aI2Po6rzJcd%20%20nGFoQiuyWSkMnxt3eermfzhQI9LeRMHdIiX_-40sadjp95DxNAqmWe4bgRx9zrvK7DAkl8Z6lIDH%20%20o7PC5ZQvaTPFF74I_H9oo2TXjMgvtqABGzD-SsaizgvHiYI-T1WOOkPzHlD0wpM8lDwB2uSaZGag%20%20Jr3JZc02sL1jlcaBwFKjjldfjeObJ85P7zTrXbbEWE04U99DlGINRMdtyxKswx5oLWDWd-IlJ0AT%20%20C9Yd55cCOzQsjyPlJwspxbJb8VlQ5ZIiaZmvESNSPo8BFKbR9BBlXCEM9ktWq6q67kGEhNhCavqs%20%20SbhkRq_j1WKkzUn_XM7x4OdDDACEY7ehJ2ihLwCg6RyjESG8YbUnCY8KkdRtMxGfF_GytbbnyZpK%20%20iTS4aKI_ajhPMfnz2S671sJnihkFRop_vOMuQ8JFu8UsW_jS91L9Y-uytSlPoLnhbVQj09I6RhVj%20%20yuExRpkGqbUz6YRzy__pAYYvEj8e9JwBTBkXVBnBewG7o-rKg0S3GPniP-Nwaj8
	@RequestMapping("/loginOK")
	public String loginMiduOK(@RequestParam(name = "salida_json") String usuarioJson) {
		LOGGER.info("USER logged in - SUCCESS");
		List<UsuarioSede> usuarios = null;

		Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("CERT"));

		UsuarioSede usuario = new UsuarioSede("44556612A", "", true, true, true, true, grantedAuths);
		usuario.setNombreCompleto("Javier Aliaga Montesinos");
		usuario.setDocumento("44556612A");
		usuario.setEsRepresentante(true);
		usuario.setTicket("fsdfjkd-askldsad-asdlskajd-sdsalkdjsa");

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			usuarios = objectMapper.readValue(usuarioJson, new TypeReference<List<UsuarioSede>>() {
			});
		} catch (IOException e) {
			LOGGER.error("TEMPORAL", e);
			// throw new SedeElectronicaException(ExceptionType.LOGIN_NO_OK, e);
		}

		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken("", ""));
		// boolean isAuthenticated = isAuthenticated(authentication);
		// if (isAuthenticated) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// }
		usuarios.size();
		return "redirect:/";
	}

	@RequestMapping("/loginKO")
	public String loginKO() {
		LOGGER.error("USER logged in - ERROR");
		return "redirect:/";
	}

}