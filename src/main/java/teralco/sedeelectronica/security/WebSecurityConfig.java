package teralco.sedeelectronica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider customAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// http.authorizeRequests().antMatchers("/procedimiento").authenticated();
		http.authorizeRequests().antMatchers("/admin/**").authenticated();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/admin");
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		http.anonymous().disable();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.customAuthProvider);
	}

}
