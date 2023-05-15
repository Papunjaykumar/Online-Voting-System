package com.OVS.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccesshandler implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectstrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("Entering redirection process");
		
		Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
		
		authorities.forEach(authority->{
			if(authority.getAuthority().equals("ROLE_USER")) {
				System.out.println("ROLE_USER");
				try {
					redirectstrategy.sendRedirect(request, response, "/user/home");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			else if(authority.getAuthority().equals("ROLE_ADMIN")) {
				try {
					redirectstrategy.sendRedirect(request, response, "/admin/panel");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			else if(authority.getAuthority().equals("ROLE_CANDIDATE")) {
				try {
					redirectstrategy.sendRedirect(request, response, "/candidate/home");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}else if(authority.getAuthority().equals("ROLE_VOTER")) {
				try {
					redirectstrategy.sendRedirect(request, response, "/voter/home");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			else {
				throw new IllegalStateException();				
			}
		});
		
	}

}
