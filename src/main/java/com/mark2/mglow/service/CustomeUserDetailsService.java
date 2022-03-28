package com.mark2.mglow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mark2.mglow.helper.UserRepository;
import com.mark2.mglow.model.UserData;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	boolean found = false;
	String password = "";
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		List<UserData> data = (List<UserData>)userRepo.findAll();
		
		for(UserData d : data) {
			if(d.getEmail().equals(email)) {
				found = true;
				password = d.getPassword();
				System.out.println(found);
				break;
			}
		}	
		if(found) {
			System.out.println("CUSTOMEUSERDETAILSSERVICE log: user name verified");
			return new User(email,password,new ArrayList<>());
		}
		else {
			System.out.println("CUSTOMEUSERDETAILSSERVICE log: username is invalid");
			throw new UsernameNotFoundException("User not found !!");
		}
	}
}
