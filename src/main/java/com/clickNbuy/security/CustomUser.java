package com.clickNbuy.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clickNbuy.entity.User;

public class CustomUser implements UserDetails {
	
	User user;

	public CustomUser(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isEnabled() {
		System.out.println("User status: " + user.isStatus());

		return user.isStatus(true);
	}
}
