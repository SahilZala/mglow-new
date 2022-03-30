package com.mark2.mglow.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark2.mglow.helper.UserRepository;
import com.mark2.mglow.model.Response;
import com.mark2.mglow.model.UserData;


@RestController
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("mock/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("mock/signup")
	public ResponseEntity<?> signup(@RequestBody String user) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserData userData = mapper.readValue(user.getBytes(), UserData.class);
		
		userData.setUserid(""+new Date().getTime());
		
		if(userData.getMobileno() != null) {
			if(userData.getMobileno().length() != 10) {
				System.out.println("LOG: invalid length of mobileno.");
				return ResponseEntity.ok(new Response("invalid length of mobileno!",0));
			}
		}
		else {
			System.out.println("LOG: pleasse enter mobileno.");
			return ResponseEntity.ok(new Response("pleasse enter mobileno!",0));
		}
		
		
		try {
			 this.userRepo.save(userData);
			 System.out.println("LOG: success");
			 return ResponseEntity.ok(new Response("success",1));
		}
		catch(Exception ex)
		{
			System.out.println("LOG: "+ex.getMessage());
			return ResponseEntity.ok(new Response(ex.getMessage(),0));
		}
		
	}
	
}
