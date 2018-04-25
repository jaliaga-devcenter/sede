package teralco.sedeelectronica.security.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;
import teralco.sedeelectronica.pasarela.client.PasarelaClient;
import teralco.sedeelectronica.security.principal.UsuarioSede;
import teralco.sedeelectronica.security.provider.CertAuthenticationProvider;
import teralco.sedeelectronica.security.provider.CertAuthenticationToken;
import teralco.sedeelectronica.security.provider.CustomAuthenticationProvider;

public class ProcedimientoHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	@Autowired
	private PasarelaClient pasarelaClient;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${sede.midu}")
	private String miduUrlPattern;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String salidaJson = request.getParameter("salida_json");

		boolean noHayUsuario = authentication == null && salidaJson == null;
		boolean hayUsuarioAdmin = authentication != null
				&& request.isUserInRole(CustomAuthenticationProvider.ROLE_ADMIN_SEDE) && salidaJson == null;

		if (noHayUsuario || hayUsuarioAdmin) {
			String returnTo = request.getRequestURL().toString();
			try {
				Map<String, Object> uriVariables = new HashMap<>();
				uriVariables.put("returnTo", returnTo);
				String url = UriComponentsBuilder.fromHttpUrl(this.miduUrlPattern).buildAndExpand(uriVariables)
						.toString();
				response.sendRedirect(url);
			} catch (IOException e) {
				throw new SedeElectronicaException(ExceptionType.LOGIN_NO_OK, e);
			}

			return false;
		}

		boolean hayUsuarioSede = authentication != null && authentication.isAuthenticated()
				&& request.isUserInRole(CertAuthenticationProvider.ROLE_USER_SEDE);

		if (hayUsuarioSede) {
			return true;
		}

		String jsonDesencriptado = this.pasarelaClient.desencriptar(salidaJson);

		ObjectMapper objectMapper = new ObjectMapper();
		List<UsuarioSede> usuarios = null;
		try {
			usuarios = objectMapper.readValue(jsonDesencriptado, new TypeReference<List<UsuarioSede>>() {
			});
		} catch (IOException e) {
			throw new SedeElectronicaException(ExceptionType.LOGIN_NO_OK, e);
		}

		UsuarioSede user = usuarios.get(0);

		authentication = this.authenticationManager.authenticate(new CertAuthenticationToken(user));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return super.preHandle(request, response, handler);
	}

}
