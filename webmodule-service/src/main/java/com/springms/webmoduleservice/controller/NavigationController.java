package com.springms.webmoduleservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.StreamingHttpOutputMessage.Body;
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
    	System.out.println("Login form parameters from request : "
    			+ " | username: " + request.getParameter("username") + " |---| "
    			+ " | password: " + request.getParameter("password"));
       
    	//String response = restTemplate.getForObject("http://gateway-service/doLogin", String.class);
        
    	//TODO 
    	// 1. The username and password is available from request body. It needs to be authenticated.
    	// 2. Then we need to call /oauth/ to get token and return to front-end.
    	// 3. Also the user should be redirected to /home page automatically.
    	
    	// to be changed to /home later
    	return "login";
    }

}
