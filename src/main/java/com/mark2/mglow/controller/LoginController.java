package com.mark2.mglow.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.tomcat.util.json.JSONParser;
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
import com.mark2.mglow.model.ErrorResponse;
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
		
		userData.setUserid(new Date().getHours()+""+new Date().getTime());
		
		if(userData.getMobileno() != null) {
			if(userData.getMobileno().length() != 10) {
				return ResponseEntity.ok(new ErrorResponse("invalid length of mobileno!"));
			}
		}
		else {
			return ResponseEntity.ok(new ErrorResponse("pleasse enter mobileno!"));
		}
		
		UserData returnData;
		
		try {
			 returnData = this.userRepo.save(userData);
		}
		catch(Exception ex)
		{
			System.out.println("log: "+ex.getMessage());
			return ResponseEntity.ok(new ErrorResponse(ex.getMessage()));
		}
		return ResponseEntity.ok(returnData);
	}
	
}
