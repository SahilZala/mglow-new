package com.mark2.mglow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome to our small page";
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
}
