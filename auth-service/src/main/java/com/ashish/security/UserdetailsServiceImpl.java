package com.ashish.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashish.entity.Appuser;
import com.ashish.repo.AuthRepo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserdetailsServiceImpl implements UserDetailsService{

	private final AuthRepo authrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Appuser user=authrepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found"));
		return new CustomUserDetails(user);
	}

}
