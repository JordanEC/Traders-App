package com.jordanec.tradersapp.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanec.tradersapp.model.Supplier;
import com.jordanec.tradersapp.model.User;
import com.jordanec.tradersapp.model.User.Role;
import com.jordanec.tradersapp.repository.UserRepository;
import com.jordanec.tradersapp.service.*;
import com.jordanec.tradersapp.util.Util;
import com.jordanec.tradersapp.viewmodels.*;

@RestController
@RequestMapping("/")
public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public AuthenticationController() {}
	
	public AuthenticationController(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}
	
	
	/*@Inject
	public AuthenticationController(Log log, TradersUserService tradersUserService) {
		log.debug("*********Login ctor");
		this.log = log;
		this.tradersUserService = tradersUserService;
	}*/

	/*@RequestMapping(value = "login", method = RequestMethod.GET)
	public String getLogin(@RequestBody AuthenticationUser authenticationUser) {
		//tradersUserService.
		//model.addAttribute("authenticationUser", new AuthenticationUser());
		//return "authenticate/login";
		return "lo";
	}*/
		
	@GetMapping("login")
	public ResponseEntity<User>  login() {
		return ResponseEntity.ok(Util.currentUser().get());
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {

		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("user.already.exists");
		}

		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setRole(Role.ROLE_USER);
		userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
	
	
	/*@RequestMapping(value = "/account/register", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("registrationUser", new RegistrationUser());
		return "authenticate/register";
	}
	
	@RequestMapping(value = "/account/register", method=RequestMethod.POST)
	public String register(@Valid RegistrationUser registrationUser, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("registrationUser", registrationUser);
            return "authenticate/register";
        }
		TradersUser tradersUser = new TradersUser();
		
		tradersUser.setEmail(registrationUser.getEmail());
		tradersUser.setName(registrationUser.getName());
		tradersUser.setPassword(registrationUser.getPassword());
		tradersUser.setRepeatPassword(registrationUser.getRepeatPassword());
		
		try {
			tradersUserService.registerNewUserAccount(tradersUser);
		} catch (EmailExistsException e) {
			return register(model);
		}
		return "redirect:/";
	}*/

}
