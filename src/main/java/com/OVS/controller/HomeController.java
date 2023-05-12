package com.OVS.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.OVS.model.User;
import com.OVS.model.UserAuthentication;
import com.OVS.model.UserRole;
import com.OVS.repo.UserAuthenticationRepository;
import com.OVS.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserAuthenticationRepository userAuthRepo;
	
	@Autowired
	private UserService userserv;

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
	//handler for  registering user
	@PostMapping("/do_register")
	public String registration(@Valid @ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue="false") boolean agreement,Model model,BindingResult result,HttpSession session) {
		
		
		try {
			if(!agreement) {
				throw new Exception("You have not agreed the terms and condidtions");
			}
			user.setAdmin(false);
			user.setAuthorize(false);
			user.setRole(UserRole.USER);
			System.out.println(agreement);
			
			userAuthRepo.save(new UserAuthentication(user.getEmail(),user.getPassword(),"ROLE_"+user.getRole()));
			
			user=this.userserv.addUser(user);
			//System.out.println(user);
			
			model.addAttribute("user",user);
			session.setAttribute("classType", "alert-success");
			session.setAttribute("message", "You have successFully Registered !! ");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message","Something Went wrong !! " +e.getMessage());
			session.setAttribute("classType", "alert-danger");
			return "register";
		}
		
		
		
		return "register";
		
		
	}
	
	@GetMapping("/loginPage")
	public String doLogin() {
		return "login";
	}
	
	
}
