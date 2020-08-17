package com.microservicestest.serviceone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
