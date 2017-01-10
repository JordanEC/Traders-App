package com.jordanec.tradersapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.csrf().disable();
		 //.httpBasic()
	     // .and()
         //.authorizeRequests()
         //.antMatchers("/login").permitAll()
         //.antMatchers("/register").permitAll();
         //    .antMatchers("/resources/**", "/register").permitAll()
             //.anyRequest().authenticated()
//             .and()
//         .formLogin()
//             .loginPage("/login")             
//             .permitAll()
         //    .and()
         //.logout()
         //.logoutUrl("/logout")
         //    .permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService, PasswordEncoder encoder)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
	
	
}