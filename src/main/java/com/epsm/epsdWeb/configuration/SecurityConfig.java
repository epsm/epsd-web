package com.epsm.epsdWeb.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final String LOGIN_ENDPOINT = "/history";
    private final String LOGOUT_ENDPOINT = "/sing";
    private final String[] PUBLIC_ENDPOINTS = {"/", "sing"};
    private final String[] APPLICATION_ENDPOINTS = {"history"};
    
    @Autowired
	private DataSource dataSource;
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource).
				usersByUsernameQuery("select username, password, true from user where username=?").
				authoritiesByUsernameQuery("select username, 'ROLE_USER' from user where username=?").
				passwordEncoder(new StandardPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_ENDPOINTS).permitAll()
			.antMatchers(APPLICATION_ENDPOINTS)
			
			.permitAll().and()
			//.access("hasRole('USER')").and()
			
			.formLogin().defaultSuccessUrl(LOGIN_ENDPOINT, false).and()
			.logout().logoutUrl(LOGOUT_ENDPOINT)
			.logoutSuccessUrl(LOGOUT_ENDPOINT).and()
			.formLogin().loginPage(LOGOUT_ENDPOINT);
	}
}
