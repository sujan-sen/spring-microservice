package com.springms.webmoduleservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class NavigationController {
	
    @Autowired
    private Environment environment;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/test")
    public String health() {
        return "I am Ok. Running at port :" + environment.getProperty("local.server.port");
    }
    
    @GetMapping("/")
    public String displayLoginPage() {
        return "login";
    }
    
    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }
    
    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request) {
    	System.out.println("WebModuleService | Login form parameters from request : "
    			+ " | username: " + request.getParameter("username") + " |---| "
    			+ " | password: " + request.getParameter("password"));
    	
    	Map<String, String> paramsMap = new HashMap<String, String>();
    	paramsMap.put("username", request.getParameter("username"));
    	paramsMap.put("password", request.getParameter("password"));

    	ResponseEntity<String> response = restTemplate.postForEntity(
    			"http://gateway-service/doLogin",
    			paramsMap,
    			String.class    	            	        
    	);

    	// check response
    	if (response.getStatusCode() == HttpStatus.OK) {
    	    System.out.println("WebModuleService | Request Successful. | Response: " + response.getBody());    
    	} else {
    	    System.out.println("WebModuleService | Request Failed. | Status code: " + response.getStatusCode());    	    
    	}
           	
    	//TODO
    	//1. Return the token to front-end.
    	//2. Ideally, the user should be redirected to /home page automatically.
    	
    	// to be changed to /home later
    	return "login";
    }

}
