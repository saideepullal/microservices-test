package com.microservicestest.gatewayservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@Controller
public class AuthenticationService {

    @Autowired
    private EurekaClient eurekaClient;
	
    
    @Value("${server.landingpage}")
    private String landingpage;
    
    @Value("${serviceone.appname}")
    private String serviceAppName;
    
	@GetMapping("/login")
	public ModelAndView login() {
		
		return new ModelAndView("login");
	}
	@GetMapping("/loadsuccesspage")
	public ModelAndView loadSuccessPage(HttpServletRequest req , HttpServletResponse resp) {
		try {
			
			Application application = eurekaClient.getApplication(serviceAppName);
			InstanceInfo instanceInfo = application.getInstances().get(0);
		    RequestDispatcher dispatcher = req.getServletContext()
		    	      .getRequestDispatcher("/"+"service-one"+"/"+landingpage);
		    	    dispatcher.forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
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
