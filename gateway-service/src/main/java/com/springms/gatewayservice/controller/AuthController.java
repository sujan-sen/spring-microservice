package com.springms.gatewayservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
    private Environment environment;
	
	@PostMapping("/doLogin")
	public ResponseEntity<String> doLogin(@RequestBody Map<String, String> paramsMap) {
		String authToken = null;
		
		System.out.println("GatewayService | Running on port: " + environment.getProperty("local.server.port"));
		
		System.out.println("GatewayService | Login form parameters from webmodule-service request : "
    			+ " | username: " + paramsMap.get("username") + " |---| "
    			+ " | password: " + paramsMap.get("password"));
		
		//TODO
    	// 1. The username and password is available here. It needs to be authenticated.
    	// 2. Then we need to call /oauth/ to get token and return the same.
		
		authToken = "blahblahblah";
		return new ResponseEntity<String>(authToken, HttpStatus.OK);
	}

}
