package com.microservicestest.gatewayservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationService {
	
	@GetMapping("/login")
	public ModelAndView login() {
		
		return new ModelAndView("login");
	}
	@GetMapping("/loadsuccesspage")
	public ModelAndView loadSuccessPage() {
		
		return new ModelAndView("homepage");
	}
	@GetMapping("/callserviceone")
	public ResponseEntity<Map<String,Object>> callServiceOne() {
		Map<String,Object> model =  new HashMap<String,Object>();
		model.put("abc", "abc");
		
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";}

}
