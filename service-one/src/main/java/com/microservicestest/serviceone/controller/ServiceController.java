package com.microservicestest.serviceone.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ServiceController {
	
	@Autowired
	Environment environment;
	
	@Value("${test.property}")
	String cloudconfigProperty;
	
	@GetMapping("/getproperty")
	public ResponseEntity<Map<String,Object>> getCloudConfigProperty() {
		Map<String,Object> body = new HashMap<String,Object>();
		body.put("hardcodedProperty", "This is hardcoded");
		String sampleProperty = environment.getProperty("sample.property");
		body.put("propertyFromAppProperties", sampleProperty);
		body.put("propertyFromCloudConfig", cloudconfigProperty);
		return new ResponseEntity<Map<String,Object>>(body, HttpStatus.OK);
	}
	
	@GetMapping("/landingpage")
	public ModelAndView loadLandingPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("landingpage");
		HttpSession currentSession = request.getSession(false);
		if (currentSession != null) {
			Object securityContext = currentSession.getAttribute("securityContext");
			if (securityContext != null) {
				User currentUserSecurityContext = (User) securityContext;
				modelAndView.addObject("userName", currentUserSecurityContext.getUsername());
				modelAndView.addObject("authorities", currentUserSecurityContext.getAuthorities());
				modelAndView.addObject("isNonExpired", currentUserSecurityContext.isAccountNonExpired());
				modelAndView.addObject("isNonLocked", currentUserSecurityContext.isAccountNonLocked());
				modelAndView.addObject("hasPasswordExpired",currentUserSecurityContext.isCredentialsNonExpired());

			}
		}

		return modelAndView;
	}

}
