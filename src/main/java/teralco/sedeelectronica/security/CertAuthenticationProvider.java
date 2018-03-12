package teralco.sedeelectronica.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CertAuthenticationProvider implements AuthenticationProvider {

	protected static final String ROLE_USER_SEDE = "ROLE_USUARIO_SEDE";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority(ROLE_USER_SEDE));
		return new CertAuthenticationToken(authentication.getPrincipal(), grantedAuths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CertAuthenticationToken.class);
	}

}
