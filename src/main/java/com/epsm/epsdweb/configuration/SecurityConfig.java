package com.epsm.epsdweb.configuration;

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
	private final String LOGIN_ENDPOINT = "/app/history";
    private final String LOGOUT_ENDPOINT = "/login";
    private final String LOGIN_PAGE = "/login";
    private final String LOGOUT_URL = "/logout";
    private final String[] PUBLIC_ENDPOINTS = {"/", "login", "/api/**"};
    private final String[] SECURED_ENDPOINTS = {"/app/**"};
    private final String USERS_BY_USERNAME_QUERY =
    		"SELECT email AS username, password, true "
    		+ "FROM users "
    		+ "WHERE email = ?";
    private final String AUTHORITIES_BY_USERNAME_QUERY =
    		"SELECT email AS username, role "
    		+ "FROM users "
    		+ "WHERE email = ?";
    
    @Autowired
	private DataSource dataSource;
    
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY)
				.passwordEncoder(new StandardPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
		
        http
        	.csrf()
        		.ignoringAntMatchers("/api/**").and()
        	.authorizeRequests()
        		.antMatchers(PUBLIC_ENDPOINTS).permitAll()
				.antMatchers(SECURED_ENDPOINTS).authenticated().and()
			.formLogin()
				.loginPage(LOGIN_PAGE)
				.usernameParameter("email")
				.defaultSuccessUrl(LOGIN_ENDPOINT, false).and()
			.logout()
				.logoutUrl(LOGOUT_URL)
				.logoutSuccessUrl(LOGOUT_ENDPOINT);
	}
}
