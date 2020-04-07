package com.springms.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class OrderServiceController {
 
    @Autowired
    Environment environment;
 
    @GetMapping("/")
    public String health() {
        return "I am Ok";
    }
    @HystrixCommand(fallbackMethod = "backend_Fallback")
    @GetMapping("/backend")
    public String backend() {
        System.out.println("Inside OrderServiceController::backend...");
 
        String serverPort = environment.getProperty("local.server.port");
 
        System.out.println("Port : " + serverPort);
 
        return "Hello form Backend!!! " + " Host : localhost " + " :: Port : " + serverPort;
    }
    
    @SuppressWarnings("unused")
	private String backend_Fallback() {
    	System.out.println("Inside OrderServiceController::backend_Fallback...");
    	String serverPort = environment.getProperty("local.server.port");
    	 
        System.out.println("Port : " + serverPort);
 
        return "Sorry backend URL is not working and you are seeing a Fallback Response!! " + " Host : localhost " + " :: Port : " + serverPort;
    	
    }
}
