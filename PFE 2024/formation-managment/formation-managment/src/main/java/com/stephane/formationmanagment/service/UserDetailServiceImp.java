package com.stephane.formationmanagment.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stephane.formationmanagment.entities.User;
import com.stephane.formationmanagment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> opt =  userRepository.findByEmail(email);
		
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
			
		}
		return new org.springframework.security.core.userdetails.User(opt.get().getEmail(), opt.get().getPassword(), new ArrayList<>());
		
	}

}
