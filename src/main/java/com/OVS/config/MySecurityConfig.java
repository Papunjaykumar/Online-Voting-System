package com.OVS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private SimpleAuthenticationSuccesshandler successHandler;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationprovider=new DaoAuthenticationProvider();
		daoAuthenticationprovider.setUserDetailsService(userdetailservice);
		daoAuthenticationprovider.setPasswordEncoder(getPasswordEncode());
		
		return daoAuthenticationprovider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/image/favicon.ico").permitAll()
			.antMatchers("/do_register","js/**","/image/**","/css/**").permitAll()
			
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/voter/**").hasRole("VOTER")
			.antMatchers("/candidate/**").hasRole("CANDIDATE")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/loginPage")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/index.html/",true)
			.successHandler(successHandler)
			.and()
			.logout()
		//	.logoutSuccessUrl("/loginPage")
			.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	
	
}
