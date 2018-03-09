package teralco.sedeelectronica.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import teralco.sedeelectronica.auth.client.AuthClient;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AuthClient authClient;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		if (!this.authClient.tienePermiso(0, username, password, "ADMCONTSEDE")) {
			throw new BadCredentialsException("External system authentication failed");
		}

		return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
