package com.OVS.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OVS.model.User;
import com.OVS.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userserv;
	
	 @GetMapping("/home")
	public String dashboard(Model model,Principal principal) {
		String email=principal.getName();
		System.out.println(email);
		User user=this.userserv.getUserByEmail(email);
		model.addAttribute("user",user);
		System.out.println(user);
		return "user/user_dashboard";
	}
	
	/*
	 * @GetMapping("/changePassword") public String changePassword() {
	 * 
	 * return "change_password"; }
	 */
	/* @GetMapping("/viewProfile") */
	 

}
