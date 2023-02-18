package io.javabrains.springbootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManagerBuilder auth;
	
	@RequestMapping("/")
	public String home() {
		return ("<h1>Welcome</h1>");
	}
	
	@RequestMapping("/user")
	public String user() throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return ("<h1>Welcome User</h1>"+username);
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}

}
