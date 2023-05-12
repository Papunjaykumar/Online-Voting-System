package com.OVS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private SimpleAuthenticationSuccesshandler successHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userdetailservice);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/image/favicon.ico").permitAll()
			.antMatchers("/index","/register","js/**","/images/**").permitAll()
			.antMatchers("/home/**").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/voter/**").hasRole("VOTER")
			.antMatchers("/candidate/**").hasRole("CANDIDATE")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/loginPage")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/index.html/",true)
			.successHandler(successHandler)
			.and()
			.logout()
			.logoutSuccessUrl("/loginPage")
			.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncode() {
		return NoOpPasswordEncoder.getInstance();
	}
}
