package com.nwchecker.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nwchecker.server.model.Role;
import com.nwchecker.server.model.User;

public class UserDetailsServiceImpl  implements UserDetailsService {
	

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getUserByUsername(username);

		if(user == null) {
			throw new UsernameNotFoundException(String.format("User not found: %s", username));
		}

		final Collection<SimpleGrantedAuthority> authorities = loadAuthorities(user);

		return createUserDetails(user, authorities);
	}

	
	private Collection<SimpleGrantedAuthority> loadAuthorities(User user) {
		final List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

		for(Role authority : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		
		return grantedAuthorities;
	}

	
	private UserDetails createUserDetails(User user, Collection<SimpleGrantedAuthority> authorities) {
		final boolean enabled = user.getEnabled();

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, true, true, true, authorities);
	}

}
