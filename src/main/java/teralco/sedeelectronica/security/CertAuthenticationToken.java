package teralco.sedeelectronica.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CertAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private Object principal;

	public CertAuthenticationToken(Object pPrincipal) {
		super(null);
		this.principal = pPrincipal;
		super.setAuthenticated(false);
	}

	public CertAuthenticationToken(Object pPrincipal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = pPrincipal;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}
