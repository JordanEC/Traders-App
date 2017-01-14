package com.jordanec.tradersapp.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import com.jordanec.tradersapp.repository.UserRepository;

import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 //http.csrf().disable();
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
		http.httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint())
			.and()
			.formLogin().and()
			
			.authorizeRequests()
				//.antMatchers("/login").permitAll()	
				.antMatchers("/register").anonymous()
				.antMatchers("/api/v2/**").authenticated()
				.anyRequest().permitAll()
			.and().logout()
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.logoutUrl("/logout")// /api/auth/logout
			.and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	
		if (Stream.of(environment.getActiveProfiles()).noneMatch(p -> p.contains("prod"))) {
			http.csrf().disable().headers().frameOptions().disable();
	}
	}
	
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService, PasswordEncoder encoder)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}*/
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(
				username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("")))
				.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
	}

	// for support of spEL in spring-data-jpa queries
	@Bean
	EvaluationContextExtension securityExtension() {
		return new SecurityEvaluationContextExtension();
	}
}