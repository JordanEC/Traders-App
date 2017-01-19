package com.jordanec.tradersapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanec.tradersapp.model.User;
import com.jordanec.tradersapp.repository.UserRepository;

@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		if (userRepository.findOneByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping("login")
	public Principal user(Principal principal) {
		return principal;
	}	
}
