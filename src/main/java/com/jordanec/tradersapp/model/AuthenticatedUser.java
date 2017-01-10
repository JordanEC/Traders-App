package com.jordanec.tradersapp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails {
	private String name;
	private String email;
	private String password;
	private long id;

	public AuthenticatedUser(User user){
		name = user.getName();
		email = user.getEmail();
		password = user.getPassword();
		id = user.getId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private static final long serialVersionUID = 5639683223516504866L;

	@Override
	public String getPassword() {
		return password;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	@Override
	public String getUsername() {
		return getEmail();
	}

	public String getEmail() {
		return email;
	}
	
}
