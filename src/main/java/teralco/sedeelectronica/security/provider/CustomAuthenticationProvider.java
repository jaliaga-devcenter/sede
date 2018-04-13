package teralco.sedeelectronica.security.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import teralco.sedeelectronica.auth.client.AuthClient;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public static final String ADMIN_SEDE = "ADMIN_SEDE";
	public static final String ROLE_ADMIN_SEDE = "ROLE_" + ADMIN_SEDE;

	@Value("${sede.entidad}")
	private Integer entidad;

	@Autowired
	private AuthClient authClient;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		try {
			if (!this.authClient.tienePermiso(0, username, password, "ADMCONTSEDE")) {
				throw new BadCredentialsException("External system authentication failed");
			}
		} catch (Exception e) {
			throw new BadCredentialsException("External system authentication failed", e);
		}

		Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority(ROLE_ADMIN_SEDE));
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
