package com.mark2.mglow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mark2.mglow.helper.JwtUtil;
import com.mark2.mglow.model.Response;
import com.mark2.mglow.model.JwtRequest;
import com.mark2.mglow.model.JwtResponse;
import com.mark2.mglow.service.CustomeUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager; 

	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)
	{
		System.out.println(jwtRequest.toString());
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
			try {
				UserDetails userDetails  = this.customeUserDetailsService.loadUserByUsername(jwtRequest.getUsername());		
				String token = this.jwtUtil.generateToken(userDetails);
				System.out.println("JWT "+token);
				return ResponseEntity.ok(new JwtResponse(token));
			}
			catch(Exception ex)
			{
				System.out.println("JWTCONTROLLER log: "+ex.getMessage());
				return ResponseEntity.ok(new Response(ex.getMessage()));
			}
		}
		catch(UsernameNotFoundException ex) {
			System.out.println("JWTCONTROLLER log: "+ex.getMessage());
			ex.getStackTrace();
			return ResponseEntity.ok(new Response(ex.getMessage()));
			
		}
		catch(Exception ex) {
			
			System.out.println("JWTCONTROLLER log: "+ex.getMessage());
			ex.printStackTrace();
			return ResponseEntity.ok(new Response(ex.getMessage()));
		}
		
		//final area
		
	}
}
