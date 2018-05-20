package com.example.backoffice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backoffice.entity.User;
import com.example.backoffice.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOpt = userRepository.findByUsername(username);

		userOpt.orElseThrow(() -> new UsernameNotFoundException("The requested user is not found"));

		//return new LoginUserDetails(userOpt.get());
		return new LoginUserDetails(userOpt.get(), getAuthorities(userOpt.get()));
	}

	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (user.isAdminUserFlag()) {
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (user.isSystemUserFlag()) {
			list.add(new SimpleGrantedAuthority("ROLE_SYSTEM"));
		}
		return list;
	}
}
