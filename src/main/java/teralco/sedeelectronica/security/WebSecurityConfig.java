package teralco.sedeelectronica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthProvider;

	@Autowired
	private CertAuthenticationProvider certAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/admin/**").hasRole(CustomAuthenticationProvider.ADMIN_SEDE);
		http.formLogin().loginPage("/login").defaultSuccessUrl("/admin").failureUrl("/login?error");
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		http.anonymous().disable();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.customAuthProvider);
		auth.authenticationProvider(this.certAuthProvider);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
