package com.OVS.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.OVS.model.User;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
       // UserDto user = new UserDto();
		User user=new User();
        model.addAttribute("user", user);
        return "register";
    }
	
	@GetMapping("/login")
	public String doLogin() {
		return "login";
	}
	
	
}
