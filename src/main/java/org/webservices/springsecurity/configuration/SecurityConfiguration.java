package org.webservices.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	public SecurityConfiguration() {
		super();
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	        .csrf().disable()
	        .authorizeRequests()
	        .and()
	        .exceptionHandling().accessDeniedPage("/Access_Denied")
	        .and()
	        .authorizeRequests()
	        .antMatchers("/restws/**").authenticated()
	        .antMatchers("/services/**").authenticated()
	        .and()
	        .httpBasic();
	}



	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

}
